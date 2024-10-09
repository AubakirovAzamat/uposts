package com.mycompany.uposts.dao;

import com.mycompany.uposts.domain.api.search.searchTags.TagResp;
import java.util.List;

public interface SearchDao {
    List<TagResp> searchTags(String partTag);
}
