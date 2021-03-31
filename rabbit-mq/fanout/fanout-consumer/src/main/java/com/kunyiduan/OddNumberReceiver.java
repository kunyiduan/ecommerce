package com.kunyiduan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/21 14:41:00
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.config.queue.odd}", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.FANOUT)))
@Slf4j
public class OddNumberReceiver {

    @RabbitHandler
    public void process(Integer msg) {
        if (msg % 2 != 0) {
            log.info("OddNumberReceiver -------" + msg);
        }
    }

}
