package com.mycompany.uposts.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.dao.user.UserDao;
import com.mycompany.uposts.domain.api.common.CommonPostsResp;
import com.mycompany.uposts.domain.api.common.PostResp;
import com.mycompany.uposts.domain.api.user.login.LoginReq;
import com.mycompany.uposts.domain.api.user.login.LoginResp;
import com.mycompany.uposts.domain.api.user.publicPost.PublicPostReq;
import com.mycompany.uposts.domain.api.user.registration.RegistrationReq;
import com.mycompany.uposts.domain.api.user.registration.RegistrationResp;
import com.mycompany.uposts.domain.constant.Code;
import com.mycompany.uposts.domain.dto.User;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.domain.response.exception.CommonException;
import com.mycompany.uposts.service.common.CommonService;
import com.mycompany.uposts.util.EncryptUtils;
import com.mycompany.uposts.util.ValidationUtils;

import java.util.UUID;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ValidationUtils validationUtils;
    private final EncryptUtils encryptUtils;
    private final CommonDao commonDao;
    private final UserDao userDao;
    private final CommonService commonService;

    @Override
    public ResponseEntity<Response> getMyPosts(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);

        List<PostResp> posts = userDao.getPostsByUserId(userId);
        commonService.postEnrichment(posts);

        return new ResponseEntity<>(
                SuccessResponse.builder().data(CommonPostsResp.builder().posts(posts).build()).build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken) {

        validationUtils.validationRequest(req);

        long userId = commonDao.getUserIdByToken(accessToken);
        long postId = userDao.addPost(userId, req.getText());
        log.info("userId: {}, postId: {}", userId, postId);

        for (String tag : req.getTags()) {
            userDao.addTag(tag);
            userDao.addPostTag(postId, tag);
        }

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> login(LoginReq req) {

        validationUtils.validationRequest(req);

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        String accessToken = userDao.getAccessToken(
                User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(
                SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken).build()).build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {

        validationUtils.validationRequest(req);

        if (userDao.isExistsNickname(req.getAuthorization().getNickname()))
            throw CommonException.builder().code(Code.NICKNAME_BUSY)
                    .userMessage("Этот ник уже занят, придумайте другой").httpStatus(HttpStatus.BAD_REQUEST).build();

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        userDao.insertNewUser(User.builder().nickname(req.getAuthorization().getNickname())
                .encryptPassword(encryptPassword).accessToken(accessToken).build());

        return new ResponseEntity<>(
                SuccessResponse.builder().data(RegistrationResp.builder().accessToken(accessToken).build()).build(),
                HttpStatus.OK);
    }
}
