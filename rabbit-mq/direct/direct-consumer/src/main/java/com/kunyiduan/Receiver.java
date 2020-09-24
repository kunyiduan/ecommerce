package com.kunyiduan;

import lombok.extern.slf4j.Slf4j;
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
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.config.queue.time.name}", autoDelete = "false"),
        exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.DIRECT),
        key = "${mq.config.queue.time.routing.key}"))
@Slf4j
public class Receiver {

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        Thread.sleep(20);
        log.info("the message is " + msg);
    }

}
