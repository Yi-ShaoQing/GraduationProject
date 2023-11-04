package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.User;
import com.ysq.enums.AppHttpCodeEnum;
import com.ysq.exception.SystemException;
import com.ysq.service.MallLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MallLoginController {
    @Autowired
    private MallLoginService mallLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //如果用户名为空则抛出自定义异常
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return mallLoginService.login(user);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return mallLoginService.logout();
    }
}
