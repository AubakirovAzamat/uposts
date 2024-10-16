package com.mycompany.uposts.dao.communication;

import org.springframework.stereotype.Service;
import com.mycompany.uposts.domain.api.common.PostResp;
import com.mycompany.uposts.domain.api.common.UserResp;
import java.util.List;

@Service
public interface ReactionDao {
    void deleteLikePost(long userId, long postId);

    void likePost(long userId, long postId);
}
