package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.User;
import com.ysq.enums.AppHttpCodeEnum;
import com.ysq.exception.SystemException;
import com.ysq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //    用户注册
    @PostMapping("/register")
    public ResponseResult login(@RequestBody User user) {
        return userService.regiester(user);
    }

    @GetMapping("/list")
    public ResponseResult getUser() {

        ResponseResult result = userService.myGetUser();
        return result;
    }
//    更新用户头像
    @GetMapping("/updateAvatar")
    public ResponseResult updateAvatar(@RequestParam(value = "avatar") String avatar) {

        userService.updateAvatar(avatar);
        return userService.getInfo();
    }
}
