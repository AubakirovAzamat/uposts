package com.mycompany.uposts.service.communication;

import org.springframework.http.ResponseEntity;
import com.mycompany.uposts.domain.response.Response;

public interface ReactionService {
    ResponseEntity<Response> deleteLikePost(String accessToken, long postId);

    ResponseEntity<Response> likePost(String accessToken, long postId);
}
