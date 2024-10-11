package com.mycompany.uposts.domain.api.search.searchPostsByPartWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.api.common.PostResp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostsByPartWordResp {
    private List<PostResp> posts;
}
