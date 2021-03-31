package com.kunyiduan;

import com.kunyiduan.jwt.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Test
    public void getValue() {
        JwtUtil jwtUtil = new JwtUtil();
        System.out.println(jwtUtil.getExpired());
    }

}
