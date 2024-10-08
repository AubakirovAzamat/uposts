package com.mycompany.uposts.domain.api.search.searchTags;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.mycompany.uposts.domain.api.common.TagResp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagsResp {
    private List<TagResp> tags;
}
