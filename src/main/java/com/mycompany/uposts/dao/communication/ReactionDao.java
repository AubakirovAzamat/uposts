package com.mycompany.uposts.dao.communication;

import org.springframework.stereotype.Service;

import com.mycompany.uposts.domain.api.common.UserResp;
import com.mycompany.uposts.domain.api.communication.reaction.commentPost.CommentPostReq;
import com.mycompany.uposts.domain.dto.WhoseComment;

import java.util.List;

@Service
public interface ReactionDao {
    void commentPost(long userId, CommentPostReq req);

    void deleteLikePost(long userId, long postId);

    void likePost(long userId, long postId);

    void deleteComment(long commentId);

    WhoseComment whoseComment(long commentId);

    void unblockUser(long userId, long blockUserId);

    List<UserResp> getBlockUsers(long userId);

    void blockUser(long userId, long blockUserId);  
}
