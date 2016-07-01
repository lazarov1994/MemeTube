package com.memetube.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.memetube.models.User;
import com.memetube.rowmapper.UserRowMapper;

@Repository
public class UserRepositoryJdbc implements UserRepository {
    private final RowMapper<User> userRowMapper = new UserRowMapper();
    @Autowired
    private JdbcTemplate jdbcTmpl;

    @Override
    public void insertUser(String username, String password) {
        jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = " INSERT INTO user(user.username, user.password) VALUES(?, ?) ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                return ps;
            }
        }, new PreparedStatementCallback<User>() {

            @Override
            public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
            	ps.execute();
                return null;
            }
        });

    }

    @Override
    public User getUserByUsername(String username) {
        User user = jdbcTmpl.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = " SELECT id, username, password FROM user WHERE username = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                return ps;
            }
        }, new PreparedStatementCallback<User>() {

            @Override
            public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getResultSet();
                User u = new User();
                if (rs.next()) {
                    u = userRowMapper.mapRow(rs, rs.getRow());
                }
                return u;
            }
        });
        return user;

    }

}
