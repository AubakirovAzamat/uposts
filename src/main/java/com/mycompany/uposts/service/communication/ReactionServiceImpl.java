package com.mycompany.uposts.service.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.dao.communication.ReactionDao;
import com.mycompany.uposts.domain.api.communication.comment.CommentPostReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.util.ValidationUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;
    private final ReactionDao reactionDao;

    @Override
    public ResponseEntity<Response> deleteLikePost(String accessToken, long postId) {
        validationUtils.validationDecimalMin("postId", postId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.deleteLikePost(userId, postId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> likePost(String accessToken, long postId) {
        validationUtils.validationDecimalMin("postId", postId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.likePost(userId, postId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> commentPost(String accessToken, CommentPostReq req) {
        validationUtils.validationRequest(req);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.commentPost(userId, req);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
