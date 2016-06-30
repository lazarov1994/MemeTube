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
    public List<Meme> getMemesForCategory(Integer categoryId, int page, int pageSize) {
        List<Meme> memes = jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql;
                if (categoryId == null) {
                    sql = " SELECT id, title, image, category_id, user_id FROM meme ";
                    PreparedStatement ps = con.prepareStatement(sql);
                    return ps;

                } else {
                    sql = " SELECT id, title, image, category_id, user_id FROM meme WHERE category_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, categoryId);
                    return ps;
                }
            }
        }, new PreparedStatementCallback<List<Meme>>() {

            @Override
            public List<Meme> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getResultSet();
                List<Meme> m = new ArrayList<Meme>();
                while (rs.next()) {
                    m.add(memeRowMapper.mapRow(rs, rs.getRow()));
                }
                return m;
            }
        });

        return memes;
    }

}
