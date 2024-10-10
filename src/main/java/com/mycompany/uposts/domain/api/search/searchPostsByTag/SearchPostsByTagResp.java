package com.mycompany.uposts.domain.api.search.searchPostsByTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostsByTagResp {
    private List<PostResp> posts;
}