package com.memetube.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.memetube.models.VoteMeme;

public class VoteMemeRowMapper implements RowMapper<VoteMeme> {

    @Override
    public VoteMeme mapRow(ResultSet rs, int rowNum) throws SQLException {
        VoteMeme entry = new VoteMeme();
        entry.setId(rs.getInt("ID"));
        entry.setUserId(rs.getInt("USER_ID"));
        entry.setCategoryId(rs.getInt("CATEGORY_ID"));
        entry.setUpvotes(rs.getInt("UPVOTES"));
        entry.setDownvotes(rs.getInt("DOWNVOTES"));
        entry.setTitle(rs.getString("TITLE"));
        entry.setImage(rs.getString("IMAGE"));
        return entry;
    }

}