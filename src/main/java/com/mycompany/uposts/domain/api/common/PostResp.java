package com.mycompany.uposts.domain.api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private long countLikes;
}
