package com.mycompany.uposts.controller.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mycompany.uposts.domain.api.communication.reaction.commentPost.CommentPostReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.service.communication.ReactionService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("post-service-public/communication/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    @DeleteMapping("/deleteLikePost/{postId}")
    public ResponseEntity<Response> deleteLikePost(@RequestHeader String accessToken, @PathVariable long postId) {
        log.info("START endpoint deleteLikePost  accessToken: {}, postId: {}", accessToken, postId);
        ResponseEntity<Response> resp = reactionService.deleteLikePost(accessToken, postId);
        log.info("END endpoint deleteLikePost, response: {}", resp);
        return resp;
    }

    @PostMapping("/likePost/{postId}")
    public ResponseEntity<Response> likePost(@RequestHeader String accessToken, @PathVariable long postId) {
        log.info("START endpoint likePost  accessToken: {}, postId: {}", accessToken, postId);
        ResponseEntity<Response> resp = reactionService.likePost(accessToken, postId);
        log.info("END endpoint likePost, response: {}", resp);
        return resp;
    }

    @PostMapping("/commentPost")
    public ResponseEntity<Response> commentPost(@RequestHeader String accessToken,
            @RequestBody final CommentPostReq req) {
        log.info("START endpoint commentPost  accessToken: {}, req: {}", accessToken, req);
        ResponseEntity<Response> resp = reactionService.commentPost(accessToken, req);
        log.info("END endpoint commentPost, response: {}", resp);
        return resp;
    }

    @DeleteMapping("/deleteCommentPost/{commentId}")
    public ResponseEntity<Response> deleteCommentPost(@RequestHeader String accessToken,
            @PathVariable long commentId) {
        log.info("START endpoint deleteCommentPost  accessToken: {}, commentId: {}", accessToken, commentId);
        ResponseEntity<Response> resp = reactionService.deleteCommentPost(accessToken, commentId);
        log.info("END endpoint deleteCommentPost, response: {}", resp);
        return resp;
    }
}
