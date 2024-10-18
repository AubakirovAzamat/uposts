package com.mycompany.uposts.controller.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.mycompany.uposts.domain.api.communication.subscribe.subscription.SubscriptionReq;
import com.mycompany.uposts.domain.api.communication.subscribe.unsubscription.UnsubscriptionReq;
import com.mycompany.uposts.domain.response.Response;
import com.mycompany.uposts.service.communication.SubscriptionService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("post-service-public/communication/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/getMySubscribers")
    public ResponseEntity<Response> getMySubscribers(@RequestHeader String accessToken) {
        log.info("START endpoint getMySubscribers  accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMySubscribers(accessToken);
        log.info("END endpoint getMySubscribers, response: {}", resp);
        return resp;
    }

    @GetMapping("/getMyPublishers")
    public ResponseEntity<Response> getMyPublishers(@RequestHeader String accessToken) {
        log.info("START endpoint getMyPublishers  accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMyPublishers(accessToken);
        log.info("END endpoint getMyPublishers, response: {}", resp);
        return resp;
    }

    @PostMapping("/unsubscription")
    public ResponseEntity<Response> unsubscription(@RequestHeader String accessToken,
            @RequestBody final UnsubscriptionReq req) {
        log.info("START endpoint unsubscription  accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.unsubscription(req, accessToken);
        log.info("END endpoint unsubscription, response: {}", resp);
        return resp;
    }

    @PostMapping("/subscription")
    public ResponseEntity<Response> subscription(@RequestHeader String accessToken,
            @RequestBody final SubscriptionReq req) {
        log.info("START endpoint subscription  accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.subscription(req, accessToken);
        log.info("END endpoint subscription, response: {}", resp);
        return resp;
    }

    @GetMapping("/getMyPublishersPosts/{from}/{limit}")
    public ResponseEntity<Response> getMyPublishersPosts(@RequestHeader String accessToken, @PathVariable int from,
            @PathVariable int limit) {
        log.info("START endpoint getMyPublishersPosts   accessToken: {}, from: {}, limit {}", accessToken, from, limit);
        ResponseEntity<Response> resp = subscriptionService.getMyPublishersPosts(accessToken, from, limit);
        log.info("END endpoint getMyPublishersPosts , response: {}", resp);
        return resp;
    }
}
