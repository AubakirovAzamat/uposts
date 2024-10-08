package com.mycompany.uposts.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.uposts.dao.UserDao;
import com.mycompany.uposts.domain.constant.Code;
import com.mycompany.uposts.domain.dto.User;
import com.mycompany.uposts.domain.entity.Post;
import com.mycompany.uposts.domain.entity.PostRowMapper;
import com.mycompany.uposts.domain.response.exception.CommonException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<String> getTagsByPostId(long postId) {
        return jdbcTemplate.queryForList("SELECT text FROM tag WHERE id IN (SELECT tag_id FROM post_tag WHERE post_id = ?);", String.class, postId);
    }


    @Override
    public List<Post> getPostsByUserId(long userId) {
        return jdbcTemplate.query("SELECT * FROM post WHERE user_id = ? ORDER BY time_insert DESC;", new PostRowMapper(), userId);
    }



    @Override
    public void addPostTag(long postId, String tag) {

        jdbcTemplate.update("INSERT IGNORE INTO post_tag(post_id,tag_id) VALUES (?, (SELECT id FROM tag WHERE text = LOWER(?)));", postId, tag);
    }



    @Override
    public void addTag(String tag) {

        jdbcTemplate.update("INSERT INTO tag(text) SELECT DISTINCT LOWER(?) FROM tag WHERE NOT EXISTS (SELECT text FROM tag WHERE text = LOWER(?));", tag, tag);
    }



    @Override
    public long addPost(long userId, String text) {

        jdbcTemplate.update("INSERT INTO post(user_id,text) VALUES (?,?);", userId, text);
        return jdbcTemplate.queryForObject("SELECT id FROM post WHERE id = LAST_INSERT_ID();", Long.class);
    }



    @Override
    public long getUserIdByToken(String accessToken) {

        try {
            return jdbcTemplate.queryForObject("SELECT id FROM user WHERE access_token = ?;", Long.class, accessToken);
        } catch (EmptyResultDataAccessException ex) {
            log.error(ex.toString());
            throw CommonException.builder().code(Code.AUTHORIZATION_ERROR).userMessage("Ошибка авторизации").httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }



    @Override
    public String getAccessToken(User user) {

        try {
            return jdbcTemplate.queryForObject("SELECT access_token FROM user WHERE nickname = ? AND password = ?;",
                    String.class, user.getNickname(), user.getEncryptPassword());
        } catch (EmptyResultDataAccessException ex) {
            log.error(ex.toString());
            throw CommonException.builder().code(Code.USER_NOT_FOUND).userMessage("Пользователь не найден").httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }



    @Override
    public boolean isExistsNickname(String nickname) {

        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM user WHERE nickname = ?);", Integer.class, nickname) != 0;
    }



    @Override
    public void insertNewUser(User user) {

        jdbcTemplate.update("INSERT INTO user(nickname,password,access_token) VALUES (?,?,?);",
                user.getNickname(), user.getEncryptPassword(), user.getAccessToken());
    }


}
