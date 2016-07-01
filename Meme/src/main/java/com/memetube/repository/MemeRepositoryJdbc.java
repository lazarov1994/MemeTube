package com.memetube.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.memetube.models.Category;
import com.memetube.models.VoteMeme;
import com.memetube.rowmapper.VoteMemeRowMapper;

@Repository
public class MemeRepositoryJdbc implements MemeRepository {

    private final RowMapper<VoteMeme> voteMemeRowMapper = new VoteMemeRowMapper();

    @Autowired
    private JdbcTemplate jdbcTmpl;

    @Override
    public VoteMeme addMeme(String title, String image, int category, int userId) {
        VoteMeme meme = jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = " INSERT INTO meme(title, image, category_id, user_id) VALUES (?, ?, ?, ?) ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, image);
                ps.setInt(3, category);
                ps.setInt(4, userId);
                return ps;
            }
        }, new PreparedStatementCallback<VoteMeme>() {

            @Override
            public VoteMeme doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getResultSet();
                VoteMeme m = new VoteMeme();
                if (rs.next()) {
                    m = (voteMemeRowMapper.mapRow(rs, rs.getRow()));
                }
                return m;
            }
        });

        return meme;

    }

    @Override
    public List<VoteMeme> getMemesForCategory(Integer categoryId, int page, int pageSize) {
        List<VoteMeme> memes = jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append(" SELECT DISTINCT m.id, m.title, m.category_id,m.image, m.user_id, ");
                sql.append(" (SELECT COUNT(CASE WHEN v.info=-1 THEN -1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id) AS downvotes, ");
                sql.append(" (SELECT COUNT(CASE WHEN v.info=1 THEN 1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id) AS upvotes ");
                sql.append(" FROM meme m ");
                sql.append(" LEFT JOIN vote v ");
                sql.append(" ON m.id = v.meme_id ");
                sql.append(" WHERE m.id = v.meme_id ");

                if (categoryId == null) {
                    sql.append(" ORDER BY ((SELECT COUNT(CASE WHEN v.info=1 THEN 1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id) - ");
                    sql.append(" (SELECT COUNT(CASE WHEN v.info=-1 THEN -1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id)) ");
                    PreparedStatement ps = con.prepareStatement(sql.toString());
                    return ps;

                } else {
                    sql.append(" AND m.category_id = ? ");
                    sql.append(" ORDER BY ((SELECT COUNT(CASE WHEN v.info=1 THEN 1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id) - ");
                    sql.append(" (SELECT COUNT(CASE WHEN v.info=-1 THEN -1 END) FROM vote v LEFT JOIN meme m ON v.meme_id = m.id)) ");
                    PreparedStatement ps = con.prepareStatement(sql.toString());
                    ps.setInt(1, categoryId);
                    return ps;
                }
            }
        }, new PreparedStatementCallback<List<VoteMeme>>() {

            @Override
            public List<VoteMeme> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getResultSet();
                List<VoteMeme> m = new ArrayList<VoteMeme>();
                while (rs.next()) {
                    m.add(voteMemeRowMapper.mapRow(rs, rs.getRow()));
                }
                return m;
            }
        });

        List<VoteMeme> neededMemes = new ArrayList<VoteMeme>();
        for (int i = (page * (pageSize - 1)); i < page * pageSize; i++) {
            neededMemes.add(memes.get(i));
        }

        return neededMemes;
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = " SELECT c.id, c.name FROM category c ";
        List<Category> allCategories = jdbcTmpl.query(sql, new ResultSetExtractor<List<Category>>() {

            @Override
            public List<Category> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Category> listOfAllHalls = new ArrayList<Category>();
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("ID"));
                    category.setName(rs.getString("NAME"));
                    listOfAllHalls.add(category);
                }
                return listOfAllHalls;
            }
        });

        return allCategories;
    }

}
