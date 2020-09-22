package com.kunyiduan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/22 11:54:00
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.config.queue.even}", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.TOPIC),
        key = "positive.*.even"))
@Slf4j
public class EvenPositiveReceiver {

    @RabbitHandler
    public void process(Integer msg) {
        log.info("EvenPositiveReceiver " + msg);
    }

}

