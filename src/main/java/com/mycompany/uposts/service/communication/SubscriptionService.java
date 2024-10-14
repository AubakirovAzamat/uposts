package com.mycompany.uposts.service.communication;

import org.springframework.http.ResponseEntity;
import com.mycompany.uposts.domain.api.communication.subscription.SubscriptionReq;
import com.mycompany.uposts.domain.api.communication.unsubscription.UnsubscriptionReq;
import com.mycompany.uposts.domain.response.Response;

public interface SubscriptionService {
    ResponseEntity<Response> getMySubscribers(String accessToken);

    ResponseEntity<Response> getMyPublishers(String accessToken);

    ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken);

    ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken);
}
