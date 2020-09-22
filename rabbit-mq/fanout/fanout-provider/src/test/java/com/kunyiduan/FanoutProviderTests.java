package com.kunyiduan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FanoutProviderApplication.class)
public class FanoutProviderTests {

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void testSend(){
        fanoutSender.send();
    }

}
