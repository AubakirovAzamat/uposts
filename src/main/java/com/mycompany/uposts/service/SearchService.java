package com.mycompany.uposts.service;

import org.springframework.http.ResponseEntity;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsReq;
import com.mycompany.uposts.domain.response.Response;

public interface SearchService {
    ResponseEntity<Response> searchTags(SearchTagsReq req, String accessToken);
    
}
