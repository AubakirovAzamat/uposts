package com.mycompany.uposts.service.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.dao.communication.ReactionDao;
import com.mycompany.uposts.dao.communication.SubscriptionDao;
import com.mycompany.uposts.domain.api.communication.reaction.commentPost.CommentPostReq;
import com.mycompany.uposts.domain.api.communication.reaction.getBlockUsers.GetBlockUsersResp;
import com.mycompany.uposts.domain.dto.WhoseComment;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.domain.response.error.ErrorResponse;
import com.mycompany.uposts.service.common.CommonService;
import com.mycompany.uposts.util.ValidationUtils;
import com.mycompany.uposts.domain.response.error.Error;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static com.mycompany.uposts.domain.constant.Code.NOT_YOUR_COMMENT;
import static com.mycompany.uposts.domain.constant.Code.NOT_BLOCK_YOURSELF;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;
    private final ReactionDao reactionDao;
    private final SubscriptionDao subscriptionDao;
    private final CommonService commonService;

    @Override
    public ResponseEntity<Response> unblockUser(String accessToken, long blockUserId) {
        validationUtils.validationDecimalMin("blockUserId", blockUserId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.unblockUser(userId, blockUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
    }

    @Override
    public ResponseEntity<Response> getBlockUsers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        return new ResponseEntity<>(SuccessResponse.builder()
                .data(GetBlockUsersResp.builder().blockUsers(reactionDao.getBlockUsers(userId)).build()).build(), OK);
    }

    @Override
    public ResponseEntity<Response> blockUser(String accessToken, long blockUserId) {
        validationUtils.validationDecimalMin("blockUserId", blockUserId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        if (userId == blockUserId)
            return new ResponseEntity<>(ErrorResponse.builder().error(
                    Error.builder().code(NOT_BLOCK_YOURSELF).userMessage("Вы не можете заблокировать себя").build())
                    .build(), BAD_REQUEST);
        reactionDao.blockUser(userId, blockUserId);
        subscriptionDao.unsubscription(blockUserId, userId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
    }

    @Override
    public ResponseEntity<Response> deleteLikePost(String accessToken, long postId) {
        validationUtils.validationDecimalMin("postId", postId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.deleteLikePost(userId, postId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
    }

    @Override
    public ResponseEntity<Response> likePost(String accessToken, long postId) {
        validationUtils.validationDecimalMin("postId", postId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        commonService.checkBlockByPostId(userId, postId);
        reactionDao.likePost(userId, postId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
    }

    @Override
    public ResponseEntity<Response> commentPost(String accessToken, CommentPostReq req) {
        validationUtils.validationRequest(req);
        long userId = commonDao.getUserIdByToken(accessToken);
        commonService.checkBlockByPostId(userId, req.getPostId());
        reactionDao.commentPost(userId, req);
        return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
    }

    @Override
    public ResponseEntity<Response> deleteCommentPost(String accessToken, long commentId) {
        validationUtils.validationDecimalMin("commentId", commentId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        WhoseComment whoseComment = reactionDao.whoseComment(commentId);
        log.info("userId: {}, whoseComment: {}", userId, whoseComment);
        if (whoseComment.getCommentUserId() == userId || whoseComment.getPostUserId() == userId) {
            reactionDao.deleteComment(commentId);
            return new ResponseEntity<>(SuccessResponse.builder().build(), OK);
        }
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code(NOT_YOUR_COMMENT)
                .userMessage("Это не ваш комментарий и не комментарий у вашей фразы")
                .build()).build(), BAD_REQUEST);
    }
}
