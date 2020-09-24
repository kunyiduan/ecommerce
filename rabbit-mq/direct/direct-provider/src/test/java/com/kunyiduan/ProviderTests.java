package com.kunyiduan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class ProviderTests {

    @Autowired
    private Sender sender;

    @Test
    public void testSend(){
        for(int i = 0;i<9999;i++){
            sender.send();
        }
    }

}
