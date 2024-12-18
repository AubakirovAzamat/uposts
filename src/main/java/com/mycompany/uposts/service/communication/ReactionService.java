package com.mycompany.uposts.service.communication;

import org.springframework.http.ResponseEntity;

import com.mycompany.uposts.domain.api.communication.reaction.commentPost.CommentPostReq;
import com.mycompany.uposts.domain.response.Response;

public interface ReactionService {
    ResponseEntity<Response> deleteLikePost(String accessToken, long postId);

    ResponseEntity<Response> likePost(String accessToken, long postId);

    ResponseEntity<Response> commentPost(String accessToken, CommentPostReq req);

    ResponseEntity<Response> deleteCommentPost(String accessToken, long commentId);

    ResponseEntity<Response> unblockUser(String accessToken, long blockUserId);

    ResponseEntity<Response> getBlockUsers(String accessToken);

    ResponseEntity<Response> blockUser(String accessToken, long blockingUserId);
}
