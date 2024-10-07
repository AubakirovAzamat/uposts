package com.mycompany.uposts.dao;

import com.mycompany.uposts.domain.dto.User;
import com.mycompany.uposts.domain.dto.User;
import com.mycompany.uposts.domain.entity.Post;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface Dao {

    List<String> getTagsByPostId(long postId);
    
    List<Post> getPostsByUserId(long userId);

    void addPostTag(long postId, String tag);

    void addTag(String tag);

    long addPost(long userId, String text);

    long getUserIdByToken(String accessToken);

    String getAccessToken(User user);

    boolean isExistsNickname(String nickname);

    void insertNewUser(User user);
}