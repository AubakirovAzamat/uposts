package com.mycompany.uposts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycompany.uposts.domain.api.user.login.LoginReq;
import com.mycompany.uposts.domain.api.user.publicPost.PublicPostReq;
import com.mycompany.uposts.domain.api.user.registration.RegistrationReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("post-service-public")
public class UserController {

    private final UserService postService;


    @GetMapping("/hello")
    public String hello() {
        String hello = "Hello, post-service! Version: 1.0.0";
        log.info(hello);
        return hello;
    }

    @PostMapping("/publicPost")
    public ResponseEntity<Response> publicPost(@RequestHeader final String accessToken, @RequestBody final PublicPostReq req) {

        log.info("START endpoint publicPost, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = postService.publicPost(req, accessToken);
        log.info("END endpoint publicPost, response: {}", resp);
        return resp;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody final LoginReq req) {

        log.info("START endpoint login, request: {}", req);
        ResponseEntity<Response> resp = postService.login(req);
        log.info("END endpoint login, response: {}", resp);
        return resp;
    }


    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@RequestBody final RegistrationReq req) {

        log.info("START endpoint registration, request: {}", req);
        ResponseEntity<Response> resp = postService.registration(req);
        log.info("END endpoint registration, response: {}", resp);
        return resp;
    }

    @GetMapping("/getMyPosts")
    public ResponseEntity<Response> getMyPosts(@RequestHeader final String accessToken) {
        log.info("START endpoint getMyPosts, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = postService.getMyPosts(accessToken);
        log.info("END endpoint getMyPosts, response: {}", resp);
        return resp;
    }
}

