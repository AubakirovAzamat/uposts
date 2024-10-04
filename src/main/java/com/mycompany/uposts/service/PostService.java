package com.mycompany.uposts.service;

import org.springframework.http.ResponseEntity;
import com.mycompany.uposts.domen.api.LoginReq;
import com.mycompany.uposts.domen.api.PublicPostReq;
import com.mycompany.uposts.domen.api.RegistrationReq;
import com.mycompany.uposts.domen.response.Response;

public interface PostService {

    ResponseEntity<Response> publicPost(PublicPostReq req, String accessToken);

    ResponseEntity<Response> login(LoginReq req);

    ResponseEntity<Response> registration(RegistrationReq req);
}
