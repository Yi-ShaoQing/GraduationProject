package com.ysq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches("123456789","$2a$10$UJuabjY93mCfn2eQBvC7xOngdSgGRDhkkxxhhpEdMYBWbufNiS8je"));
    }

}
