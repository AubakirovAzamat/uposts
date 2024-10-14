package com.mycompany.uposts.service.impl.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mycompany.uposts.dao.CommonDao;
import com.mycompany.uposts.dao.communication.SubscriptionDao;
import com.mycompany.uposts.domain.api.communication.getMyPublishers.GetMyPublishersResp;
import com.mycompany.uposts.domain.api.communication.getMySubscribers.GetMySubscribersResp;
import com.mycompany.uposts.domain.api.communication.subscription.SubscriptionReq;
import com.mycompany.uposts.domain.api.communication.unsubscription.UnsubscriptionReq;
import com.mycompany.uposts.domain.constant.Code;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.domain.response.SuccessResponse;
import com.mycompany.uposts.domain.response.exception.CommonException;
import com.mycompany.uposts.service.communication.SubscriptionService;
import com.mycompany.uposts.util.ValidationUtils;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final ValidationUtils validationUtils;
    private final SubscriptionDao subscriptionDao;
    private final CommonDao commonDao;

    @Override
    public ResponseEntity<Response> getMySubscribers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);
        return new ResponseEntity<>(
                SuccessResponse.builder()
                        .data(GetMySubscribersResp.builder()
                                .subscribers(subscriptionDao.getMySubscribers(userId)).build())
                        .build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getMyPublishers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);
        return new ResponseEntity<>(
                SuccessResponse.builder()
                        .data(GetMyPublishersResp.builder()
                                .publishers(subscriptionDao.getMyPublishers(userId)).build())
                        .build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken) {
        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);
        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, pubUserId: {},", subUserId, pubUserId);
        subscriptionDao.unsubscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken) {
        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);
        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, subscriptionUserId: {},", subUserId, pubUserId);
        if (subUserId == pubUserId)
            throw CommonException.builder().code(Code.SUBSCRIPTION_LOGIC_ERROR)
                    .userMessage("Вы не можете подписаться на себя").httpStatus(BAD_REQUEST).build();
        subscriptionDao.subscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
