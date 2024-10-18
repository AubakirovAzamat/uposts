package com.mycompany.uposts.dao.communication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.uposts.domain.api.common.PostRespRowMapper;
import com.mycompany.uposts.domain.api.communication.reaction.commentPost.CommentPostReq;
import com.mycompany.uposts.domain.dto.WhoseComment;
import com.mycompany.uposts.domain.dto.WhoseCommentRowMapper;
import com.mycompany.uposts.domain.response.exception.CommonException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static com.mycompany.uposts.domain.constant.Code.COMMENT_NOT_FOUND;

@Slf4j
@Repository
@Transactional
public class ReactionDaoImpl extends JdbcDaoSupport implements ReactionDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void deleteLikePost(long userId, long postId) {
        jdbcTemplate.update("DELETE FROM like_post WHERE post_id = ? AND user_id = ?;", postId, userId);
    }

    @Override
    public void likePost(long userId, long postId) {
        jdbcTemplate.update("INSERT IGNORE INTO like_post(post_id, user_id) VALUES (?,?);", postId, userId);
    }

    @Override
    public void commentPost(long userId, CommentPostReq req) {
        jdbcTemplate.update("INSERT IGNORE INTO comment(user_id, post_id, text) VALUES (?,?,?);", userId,
                req.getPostId(), req.getText());
    }

    @Override
    public void deleteComment(long commentId) {
        jdbcTemplate.update("DELETE FROM comment WHERE id = ?;", commentId);
    }

    @Override
    public WhoseComment whoseComment(long commentId) {
        try {
            return jdbcTemplate
                    .queryForObject("SELECT comment.user_id AS comment_user_id, p.user_id AS post_user_id " +
                            "FROM comment JOIN post p on p.id = comment.post_id " +
                            "WHERE comment.id = ?;", new WhoseCommentRowMapper(), commentId);
        } catch (EmptyResultDataAccessException e) {
            throw CommonException.builder().code(COMMENT_NOT_FOUND).userMessage("Комментарий не найден")
                    .httpStatus(BAD_REQUEST).build();
        }
    }
}