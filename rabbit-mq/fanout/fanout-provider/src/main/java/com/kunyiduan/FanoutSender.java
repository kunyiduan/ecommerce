package com.kunyiduan;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() {
        int i = 0;
        while (true) {
            rabbitTemplate.convertAndSend(exchange, "", i);
            log.info(String.valueOf(i));
            i++;
        }
    }

}
