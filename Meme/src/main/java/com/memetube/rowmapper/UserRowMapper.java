package com.memetube.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.memetube.models.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User entry = new User();
        entry.setId(rs.getInt("ID"));
        entry.setUsername(rs.getString("USERNAME"));
        entry.setPassword(rs.getString("PASSWORD"));
        return entry;
    }

}
