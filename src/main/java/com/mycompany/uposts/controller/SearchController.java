package com.mycompany.uposts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.service.SearchService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("post-service-public/search")
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/searchTags")
    public ResponseEntity<Response> searchTags (@RequestHeader String accessToken, @RequestBody final SearchTagsReq req) {

        log.info("START endpoint searchTags , accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchTags(req, accessToken);
        log.info("END endpoint searchTags , response: {}", resp);
        return resp;
    }

    @PostMapping("/searchPostsByTag")
    public ResponseEntity<Response> searchPostsByTag(@RequestHeader String accessToken, @RequestBody final SearchPostsByTagReq req) {
        log.info("START endpoint searchPostsByTag , accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchPostsByTag(req, accessToken);
        log.info("END endpoint searchPostsByTag , response: {}", resp);
        return resp;
    }
}