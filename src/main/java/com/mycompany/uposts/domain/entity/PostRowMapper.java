package com.mycompany.uposts.domain.entity;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet row, int rowNum) throws SQLException {
        return Post.builder()
                .id(row.getLong("id"))
                .userId(row.getLong("user_id"))
                .text(row.getString("text"))
                .timeInsert(row.getString("time_insert"))
                .build();
    }
}