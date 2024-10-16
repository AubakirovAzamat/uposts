package com.mycompany.uposts.service.search;

import org.springframework.http.ResponseEntity;

import com.mycompany.uposts.domain.api.search.searchPostsByPartWord.SearchPostsByPartWordReq;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsReq;
import com.mycompany.uposts.domain.api.search.searchUsersByPartNickname.SearchUsersByPartNicknameReq;
import com.mycompany.uposts.domain.response.Response;

public interface SearchService {
    ResponseEntity<Response> searchTags(SearchTagsReq req, String accessToken);
    ResponseEntity<Response> searchPostsByTag(SearchPostsByTagReq req, String accessToken);
    ResponseEntity<Response> searchPostsByPartWord(SearchPostsByPartWordReq req, String accessToken);
    ResponseEntity<Response> searchUsersByPartNickname(SearchUsersByPartNicknameReq req, String accessToken);
}
