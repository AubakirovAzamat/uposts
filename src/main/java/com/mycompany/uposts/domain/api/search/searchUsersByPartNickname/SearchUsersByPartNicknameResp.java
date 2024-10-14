package com.mycompany.uposts.domain.api.search.searchUsersByPartNickname;

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
public class SearchUsersByPartNicknameResp {
    private List<PostResp> posts;
}
