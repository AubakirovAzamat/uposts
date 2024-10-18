package com.mycompany.uposts.domain.dto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhoseCommentRowMapper implements RowMapper<WhoseComment> {
    @Override
    public WhoseComment mapRow(ResultSet row, int rowNum) throws SQLException {
        return WhoseComment.builder()
                .commentUserId(row.getLong("comment_user_id"))
                .postUserId(row.getLong("post_user_id"))
                .build();
    }
}