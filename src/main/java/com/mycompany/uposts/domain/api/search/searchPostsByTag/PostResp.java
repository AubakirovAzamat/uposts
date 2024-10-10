package com.mycompany.uposts.domain.api.search.searchPostsByTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.api.common.TagResp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResp {
    private long postId;
    private long userId;
    private String nickname;
    private String text;
    private String timeInsert;
    private List<TagResp> tags;
}
