package com.mycompany.uposts.domain.api.user.getMyPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPostsResp {
    private List<PostResp> posts;
}
