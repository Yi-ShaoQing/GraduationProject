package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.LoginUser;
import com.ysq.domain.entity.User;
import com.ysq.enums.AppHttpCodeEnum;
import com.ysq.exception.SystemException;
import com.ysq.service.AdminLoginService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.SecurityUtils;
import com.ysq.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //如果用户名为空则抛出自定义异常
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @GetMapping("/info")
    public ResponseResult getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
    @PostMapping("logout")
    public ResponseResult logout(){
        return adminLoginService.logout();
    }
}
