package com.mycompany.uposts.dao;

import org.springframework.stereotype.Service;
import com.mycompany.uposts.domen.dto.User;

@Service
public interface Dao {

    void addPostTag(long postId, String tag);

    void addTag(String tag);

    long addPost(long userId, String text);

    long getIdByToken(String accessToken);

    String getAccessToken(User user);

    boolean isExistsNickname(String nickname);

    void insertNewUser(User user);
}