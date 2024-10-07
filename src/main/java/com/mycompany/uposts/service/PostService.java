package com.mycompany.uposts.service;

import org.springframework.http.ResponseEntity;

import com.mycompany.uposts.domain.api.LoginReq;
import com.mycompany.uposts.domain.api.PublicPostReq;
import com.mycompany.uposts.domain.api.RegistrationReq;
import com.mycompany.uposts.domain.response.Response;

public interface PostService {

    ResponseEntity<Response> getMyPosts(String accessToken);

    ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken);

    ResponseEntity<Response> login(LoginReq req);

    ResponseEntity<Response> registration(RegistrationReq req);
}
