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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.memetube.models.Meme;
import com.memetube.models.VoteMeme;
import com.memetube.rowmapper.MemeRowMapper;

@Repository
public class MemeRepositoryJdbc implements MemeRepository {
    private final RowMapper<Meme> memeRowMapper = new MemeRowMapper();
    @Autowired
    private JdbcTemplate jdbcTmpl;

    @Override
    public Meme getMeme(int id) {
        Meme meme = jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "SELECT id, title, image, category_id, user_id FROM meme WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, (int) id);
                return ps;
            }
        }, new PreparedStatementCallback<Meme>() {

            @Override
            public Meme doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Meme m = new Meme();
                if (rs.next()) {
                    m = (memeRowMapper.mapRow(rs, rs.getRow()));
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
                    PreparedStatement ps = con.prepareStatement(sql.toString());
                    return ps;

                } else {
                    PreparedStatement ps = con.prepareStatement(sql.append(" WHERE m.category_id = ? ").toString());
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
                   // m.add(memeRowMapper.mapRow(rs, rs.getRow()));
                }
                return m;
            }
        });
        int numOfMemes = memes.size();
        
        if (numOfMemes <= pageSize) {
            return memes;
        } // else if (numOfMemes <){

        return memes;
    }

}
