package com.mycompany.uposts.service.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.dao.search.SearchDao;
import com.mycompany.uposts.domain.api.common.PostResp;
import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.common.CommonPostsResp;
import com.mycompany.uposts.domain.api.search.searchPostsByPartWord.SearchPostsByPartWordReq;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsReq;
import com.mycompany.uposts.domain.api.search.searchTags.SearchTagsResp;
import com.mycompany.uposts.domain.api.search.searchUsersByPartNickname.SearchUsersByPartNicknameReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.service.common.CommonService;
import com.mycompany.uposts.util.ValidationUtils;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchDao searchDao;
    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;
    private final CommonService commonService;

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
        commonService.postEnrichment(posts);
        return new ResponseEntity<>(SuccessResponse.builder().data(CommonPostsResp.builder().posts(posts).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchPostsByPartWord(SearchPostsByPartWordReq req, String accessToken) {
        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);
        List<PostResp> posts = searchDao.searchPostsByPartWord(req);
        commonService.postEnrichment(posts);
        return new ResponseEntity<>(
                SuccessResponse.builder().data(CommonPostsResp.builder().posts(posts).build()).build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchUsersByPartNickname(SearchUsersByPartNicknameReq req, String accessToken) {
        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);
        return new ResponseEntity<>(
                SuccessResponse.builder().data(searchDao.searchUsersByPartNickname(req.getPartNickname())).build(),
                HttpStatus.OK);
    }

}
