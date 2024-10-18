package com.mycompany.uposts.domain.api.communication.subscribe.getMyPublishers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mycompany.uposts.domain.api.common.UserResp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPublishersResp {
    private List<UserResp> publishers;
}
