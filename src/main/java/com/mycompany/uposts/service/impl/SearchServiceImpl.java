package com.mycompany.uposts.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mycompany.uposts.dao.CommonDao;
import com.mycompany.uposts.dao.SearchDao;
import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.PostResp;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagResp;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsReq;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsResp;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.service.SearchService;
import com.mycompany.uposts.util.ValidationUtils;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchDao searchDao;
    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;

    @Override
    public ResponseEntity<Response> searchTags(SearchTagsReq req, String accessToken) {
        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);

        List<TagResp> tagRespList = searchDao.searchTags(req.getPartTag());
        return new ResponseEntity<>(
                SuccessResponse.builder().data(SearchTagsResp.builder().tags(tagRespList).build()).build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchPostsByTag(SearchPostsByTagReq req, String accessToken) {
        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);
        List<PostResp> posts = searchDao.searchPostsByTag(req);
        for (PostResp postResp : posts) {
            List<TagResp> tags = commonDao.getTagsByPostId(postResp.getPostId());
            postResp.setTags(tags);
        }
        return new ResponseEntity<>(SuccessResponse.builder().data(SearchPostsByTagResp.builder().posts(posts).build()).build(), HttpStatus.OK);
    }

}
