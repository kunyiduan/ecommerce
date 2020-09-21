package com.kunyiduan;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/21 10:22:00
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.config.queue.direct.demo}", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.config.exchange.direct.demo}", type = ExchangeTypes.DIRECT),
        key = "${mq.config.queue.direct.demo.routing.key"))
public class receiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("接受到消息" + msg);
    }

}
