package com.mycompany.uposts.dao;

import com.mycompany.uposts.domain.api.common.PostResp;
import com.mycompany.uposts.domain.api.common.TagResp;
import com.mycompany.uposts.domain.api.search.searchPostsByPartWord.SearchPostsByPartWordReq;
import com.mycompany.uposts.domain.api.search.searchPostsByTag.SearchPostsByTagReq;
import com.mycompany.uposts.domain.api.common.UserResp;
import java.util.List;

public interface SearchDao {
    List<PostResp> searchPostsByTag(SearchPostsByTagReq req);
    List<PostResp> searchPostsByPartWord(SearchPostsByPartWordReq req);
    List<TagResp> searchTags(String partTag);
    List<UserResp> searchUsersByPartNickname(String partNickname);
}
