package com.ysq;

import com.ysq.service.impl.GoodsCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallAdminApplicationTests {
    @Autowired GoodsCategoryServiceImpl service;
    @Test
    void contextLoads() {

        System.out.println(service.goodsCategoryTree());
    }

}
