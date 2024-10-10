package com.mycompany.uposts.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.uposts.dao.CommonDao;
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
    public List<TagResp> getTagsByPostId(long postId) {
        return jdbcTemplate.query(
                "SELECT text, id FROM tag WHERE id IN (SELECT tag_id FROM post_tag WHERE post_id = ?);",
                new TagRespRowMapper(), postId);
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
}
