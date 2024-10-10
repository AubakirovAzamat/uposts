package com.mycompany.uposts.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.uposts.dao.SearchDao;
import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.common.TagRespRowMapper;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.PostResp;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.PostRespRowMapper;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class SearchDaoImpl extends JdbcDaoSupport implements SearchDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<PostResp> searchPostsByTag(SearchPostsByTagReq req) {
        return jdbcTemplate.query("SELECT post.id AS post_id, u.id AS user_id, u.nickname, post.text, post.time_insert " +
                "FROM post " +
                "         JOIN user u on post.user_id = u.id " +
                "WHERE post.id IN (SELECT post_id FROM post_tag WHERE tag_id = ?) " +
                "ORDER BY " + req.getSort().getValue() + ";", new PostRespRowMapper(), req.getTagId());
    }

    @Override
    public List<TagResp> searchTags(String partTag) {
        return jdbcTemplate.query("SELECT id, text " +
                        "FROM (" +
                        "         SELECT tag.id, text, count(tag.id) AS c " +
                        "         FROM tag " +
                        "                  JOIN post_tag pt ON tag.id = pt.tag_id " +
                        "         WHERE text LIKE CONCAT(LOWER(?), '%') " +
                        "         GROUP BY tag.id " +
                        "         ORDER BY count(tag.id) DESC) t1 " +
                        "UNION " +
                        "SELECT id, text " +
                        "FROM (" +
                        "         SELECT tag.id, text, count(tag.id) AS c " +
                        "         FROM tag " +
                        "                  JOIN post_tag pt ON tag.id = pt.tag_id " +
                        "         WHERE text LIKE CONCAT('%', LOWER(?), '%') " +
                        "         GROUP BY tag.id " +
                        "         ORDER BY count(tag.id) DESC) t2;"
                ,  new TagRespRowMapper(), partTag, partTag);
    }
}
