package com.mycompany.uposts.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.mycompany.uposts.dao.common.CommonDao;
import com.mycompany.uposts.domain.api.common.PostResp;
import com.mycompany.uposts.domain.response.exception.CommonException;

import java.util.List;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static com.mycompany.uposts.domain.constant.Code.BLOCKED;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final CommonDao commonDao;

    @Override
    public void postEnrichment(List<PostResp> posts) {
        for (PostResp postResp : posts) {
            postResp.setTags(commonDao.getTagsByPostId(postResp.getPostId()));
            postResp.setCountLikes(commonDao.getCountLikesByPostId(postResp.getPostId()));
            postResp.setComments(commonDao.getCommentsByPostId(postResp.getPostId()));
        }
    }

    @Override
    public void checkBlockByPostId(long userId, long postId) {
        long checkBlockUserId = commonDao.getUserIdByPostId(postId);
        checkBlock(userId, checkBlockUserId);
    }

    @Override
    public void checkBlockByUserId(long userId, long checkBlockUserId) {
        checkBlock(userId, checkBlockUserId);
    }

    private void checkBlock(long userId, long checkBlockUserId) {
        if (commonDao.isBlocked(userId, checkBlockUserId))
            throw CommonException.builder().code(BLOCKED).userMessage("Вы заблокированы этим юзером")
                    .httpStatus(BAD_REQUEST).build();
    }
}
