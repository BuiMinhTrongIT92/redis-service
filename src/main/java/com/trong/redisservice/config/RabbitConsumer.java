package com.trong.redisservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitConsumer {
    @RabbitListener(queues = {"${rabbitmq.queue.product-queue}"})
    public void productConsumer(String message) {
        log.info("Receive message from Product: " + message);
    }
}
