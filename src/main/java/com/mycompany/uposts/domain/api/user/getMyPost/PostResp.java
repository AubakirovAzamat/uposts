package com.mycompany.uposts.domain.api.user.getMyPost;

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
public class PostResp {
    private long postId;
    private String text;
    private String timeInsert;
    private List<TagResp> tags;
}
