package com.kunyiduan;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/21 10:22:00
 */
@Component
public class sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange")
    private String exchange;

    public void send() {
        int i = 0;
        while(true){
            rabbitTemplate.convertAndSend(exchange, "direct.demo.routing.key", "send msg --- "+i);
            i++;
        }
    }
}
