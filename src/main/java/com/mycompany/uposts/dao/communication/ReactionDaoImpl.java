package com.mycompany.uposts.dao.communication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
}