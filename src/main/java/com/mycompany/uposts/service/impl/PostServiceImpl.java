package com.mycompany.uposts.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mycompany.uposts.dao.Dao;
import com.mycompany.uposts.domain.api.*;
import com.mycompany.uposts.domain.constant.Code;
import com.mycompany.uposts.domain.dto.User;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.domain.response.exception.CommonException;
import com.mycompany.uposts.domain.entity.Post;
import com.mycompany.uposts.service.PostService;
import com.mycompany.uposts.util.EncryptUtils;
import com.mycompany.uposts.util.ValidationUtils;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ValidationUtils validationUtils;
    private final EncryptUtils encryptUtils;
    private final Dao dao;

    @Override
    public ResponseEntity<Response> getMyPosts(String accessToken) {
        long userId = dao.getUserIdByToken(accessToken);
        List<Post> postList = dao.getPostsByUserId(userId);
        List<PostResp> postRespList = new ArrayList<>();
        for (Post post : postList) {
            List<String> tags = dao.getTagsByPostId(post.getId());
            postRespList.add(PostResp.builder()
                    .id(post.getId())
                    .text(post.getText())
                    .timeInsert(post.getTimeInsert())
                    .tags(tags).build());
        }
        return new ResponseEntity<>(SuccessResponse.builder().data(GetMyPostsResp.builder().posts(postRespList).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken) {

        validationUtils.validationRequest(req);

        long userId = dao.getUserIdByToken(accessToken);
        long postId = dao.addPost(userId, req.getText());
        log.info("userId: {}, postId: {}", userId, postId);

        for (String tag : req.getTags()) {
            dao.addTag(tag);
            dao.addPostTag(postId, tag);
        }

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Response> login(LoginReq req) {

        validationUtils.validationRequest(req);

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorizationReq().getPassword());
        String accessToken = dao.getAccessToken(User.builder().nickname(req.getAuthorizationReq().getNickname()).encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {

        validationUtils.validationRequest(req);

        if (dao.isExistsNickname(req.getAuthorizationReq().getNickname()))
            throw CommonException.builder().code(Code.NICKNAME_BUSY).userMessage("Этот ник уже занят, придумайте другой").httpStatus(HttpStatus.BAD_REQUEST).build();

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorizationReq().getPassword());
        dao.insertNewUser(User.builder().nickname(req.getAuthorizationReq().getNickname()).encryptPassword(encryptPassword).accessToken(accessToken).build());

        return new ResponseEntity<>(SuccessResponse.builder().data(RegistrationResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }
}
