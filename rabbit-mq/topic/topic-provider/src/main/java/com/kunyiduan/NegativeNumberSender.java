package com.kunyiduan;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/21 14:41:00
 */
@Component
public class NegativeNumberSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException {
        int i = 0;
        while (i>-99999) {
            i--;
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend(exchange, "negative.number.odd", i);
            } else {
                rabbitTemplate.convertAndSend(exchange, "negative.number.even", i);
            }
        }
    }

}
