package com.mycompany.uposts.dao;

import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.PostResp;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import java.util.List;

public interface SearchDao {
    List<PostResp> searchPostsByTag(SearchPostsByTagReq req);
    List<TagResp> searchTags(String partTag);
}
