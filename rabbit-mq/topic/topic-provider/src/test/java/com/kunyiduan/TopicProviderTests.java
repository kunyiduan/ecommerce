package com.kunyiduan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicProviderApplication.class)
public class TopicProviderTests {

    @Autowired
    private PositiveNumberSender positiveNumberSender;

    @Autowired
    private NegativeNumberSender negativeNumberSender;

    @Test
    public void testSend() throws InterruptedException {
        positiveNumberSender.send();
        negativeNumberSender.send();
    }

}
