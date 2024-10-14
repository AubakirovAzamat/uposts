package com.mycompany.uposts.dao.communication;

import org.springframework.stereotype.Service;
import com.mycompany.uposts.domain.api.common.UserResp;
import java.util.List;

@Service
public interface SubscriptionDao {
    List<UserResp> getMySubscribers(long userId);

    List<UserResp> getMyPublishers(long userId);

    void unsubscription(long subUserId, long pubUserId);

    void subscription(long subUserId, long pubUserId);
}