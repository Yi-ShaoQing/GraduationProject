package com.ysq.service.impl;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.LoginUser;
import com.ysq.domain.entity.User;
import com.ysq.service.MallLoginService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.JwtUtil;
import com.ysq.utils.RedisCache;
import com.ysq.vo.MallLonginVo;
import com.ysq.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class MallLoginServiceImpl implements MallLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

//        AuthenticationManager进行用户认证，如果认证没通过给出相应提示
//        如果认证通过，使用userid生成一个jwt，把完整的用户信息存入redis userid作为key
//        将用户名和登录密码封装成authenticationToken传入authenticationManager进行认证操作
//        authenticationManager最终会调用UserDetailsServiceImpl  通过用户名查询用户是否存在 然后进行认证

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        如果认证失败authenticate将为空
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //把token响应给前端
        MallLonginVo mallLonginVo = new MallLonginVo();
        mallLonginVo.setToken(jwt);
        mallLonginVo.setUserInfo(userInfoVo);
        return new ResponseResult(200,"登录成功",mallLonginVo);
    }

    @Override
    public ResponseResult logout() {
//        获取SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
//        删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");

    }

}
