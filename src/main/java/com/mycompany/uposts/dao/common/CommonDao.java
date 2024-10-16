package com.mycompany.uposts.dao.common;

import org.springframework.stereotype.Service;
import com.mycompany.uposts.domain.api.common.TagResp;
import java.util.List;

@Service
public interface CommonDao {
    List<TagResp> getTagsByPostId(long PostId);

    long getUserIdByToken(String accessToken);

    void testSchedulerLock(String instanceName);
}
