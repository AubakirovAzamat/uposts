package com.mycompany.uposts.dao.communication;

import org.springframework.stereotype.Service;
import com.mycompany.uposts.domain.api.communication.comment.CommentPostReq;

import java.util.List;

@Service
public interface ReactionDao {
    void commentPost(long userId, CommentPostReq req);

    void deleteLikePost(long userId, long postId);

    void likePost(long userId, long postId);
}
