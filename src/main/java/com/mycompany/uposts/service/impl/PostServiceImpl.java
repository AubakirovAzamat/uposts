package com.mycompany.uposts.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mycompany.uposts.dao.Dao;
import com.mycompany.uposts.domen.api.*;
import com.mycompany.uposts.domen.constant.Code;
import com.mycompany.uposts.domen.dto.User;
import com.mycompany.uposts.domen.response.Response;
import com.mycompany.uposts.domen.response.SuccessResponse;
import com.mycompany.uposts.domen.response.exception.CommonException;
import com.mycompany.uposts.service.PostService;
import com.mycompany.uposts.util.EncryptUtils;
import com.mycompany.uposts.util.ValidationUtils;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ValidationUtils validationUtils;
    private final EncryptUtils encryptUtils;
    private final Dao dao;



    @Override
    public ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken) {

        validationUtils.validationRequest(req);

        long userId = dao.getIdByToken(accessToken);
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

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        String accessToken = dao.getAccessToken(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {

        validationUtils.validationRequest(req);

        if (dao.isExistsNickname(req.getAuthorization().getNickname()))
            throw CommonException.builder().code(Code.NICKNAME_BUSY).message("Этот ник уже занят, придумайте другой").httpStatus(HttpStatus.BAD_REQUEST).build();

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        dao.insertNewUser(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword).accessToken(accessToken).build());

        return new ResponseEntity<>(SuccessResponse.builder().data(RegistrationResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }
}
