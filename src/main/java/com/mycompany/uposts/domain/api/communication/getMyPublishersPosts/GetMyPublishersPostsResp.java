package com.mycompany.uposts.domain.api.communication.getMyPublishersPosts;

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
public class GetMyPublishersPostsResp {
    private List<PostResp> posts;
}
