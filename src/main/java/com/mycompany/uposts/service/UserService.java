package com.mycompany.uposts.service;

import org.springframework.http.ResponseEntity;

import com.mycompany.uposts.domain.api.user.login.LoginReq;
import com.mycompany.uposts.domain.api.user.publicPost.PublicPostReq;
import com.mycompany.uposts.domain.api.user.registration.RegistrationReq;
import com.mycompany.uposts.domain.response.Response;

public interface UserService {

    ResponseEntity<Response> getMyPosts(String accessToken);

    ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken);

    ResponseEntity<Response> login(LoginReq req);

    ResponseEntity<Response> registration(RegistrationReq req);
}
