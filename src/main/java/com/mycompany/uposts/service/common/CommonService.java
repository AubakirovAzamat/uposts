package com.mycompany.uposts.service.common;

import com.mycompany.uposts.domain.api.common.PostResp;
import java.util.List;

public interface CommonService {
    void postEnrichment(List<PostResp> posts);

    void checkBlockByPostId(long userId, long postId);

    void checkBlockByUserId(long userId, long checkBlockUserId);
}