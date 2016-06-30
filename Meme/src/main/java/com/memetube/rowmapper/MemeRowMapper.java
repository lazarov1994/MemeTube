package com.memetube.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.memetube.models.Meme;

public class MemeRowMapper implements RowMapper<Meme> {

    @Override
    public Meme mapRow(ResultSet rs, int rowNum) throws SQLException {
        Meme entry = new Meme();
        entry.setId(rs.getInt("ID"));
        entry.setUserId(rs.getInt("USER_ID"));
        entry.setCategoryId(rs.getInt("CATEGORY_ID"));
        entry.setTitle(rs.getString("TITLE"));
        entry.setImage(rs.getString("IMAGE"));
        return entry;
    }

}