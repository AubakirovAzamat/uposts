package com.mycompany.uposts.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.domain.api.common.PostResp;
import java.util.List;

@Slf4j  
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final CommonDao commonDao;

    @Override
    public void postEnrichment(List<PostResp> posts) {
        for (PostResp postResp : posts) {
            postResp.setTags(commonDao.getTagsByPostId(postResp.getPostId()));
            postResp.setCountLikes(commonDao.getCountLikes(postResp.getPostId()));
        }
    }
}
