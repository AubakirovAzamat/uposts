package com.mycompany.uposts.dao.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.uposts.domain.api.common.CommentResp;
import com.mycompany.uposts.domain.api.common.CommentRespRowMapper;
import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.common.TagRespRowMapper;
import com.mycompany.uposts.domain.constant.Code;
import com.mycompany.uposts.domain.response.exception.CommonException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class CommonDaoImpl extends JdbcDaoSupport implements CommonDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<CommentResp> getCommentsByPostId(long postId) {

        try {
            return jdbcTemplate.query("SELECT comment.id AS comment_id, user_id, nickname, text, comment.time_insert " +
                    "FROM comment " +
                    "         JOIN user u on u.id = comment.user_id " +
                    "WHERE post_id = ? " +
                    "ORDER BY comment.time_insert DESC;", new CommentRespRowMapper(), postId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getCountLikesByPostId(long postId) {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM like_post WHERE post_id = ?;", Long.class, postId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<TagResp> getTagsByPostId(long postId) {

        try {
            return jdbcTemplate.query(
                    "SELECT text, id FROM tag WHERE id IN (SELECT tag_id FROM post_tag WHERE post_id = ?);",
                    new TagRespRowMapper(), postId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getUserIdByToken(String accessToken) {

        try {
            return jdbcTemplate.queryForObject("SELECT id FROM user WHERE access_token = ?;", Long.class, accessToken);
        } catch (EmptyResultDataAccessException ex) {
            log.error(ex.toString());
            throw CommonException.builder().code(Code.AUTHORIZATION_ERROR).userMessage("Ошибка авторизации")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public void testSchedulerLock(String instanceName) {

        jdbcTemplate.update("INSERT INTO test_scheduler_lock(instance_name) VALUES (?);", instanceName);
    }

    @Override
    public long getUserIdByPostId(long postId) {
        return jdbcTemplate.queryForObject("SELECT user_id FROM post WHERE id = ?;", Long.class, postId);
    }
    @Override
    public boolean isBlocked(long userId, long checkBlockUserId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT * FROM block WHERE user_id = ? AND block_user_id = ?) AS result;", Integer.class, checkBlockUserId, userId) != 0;
    }

}
