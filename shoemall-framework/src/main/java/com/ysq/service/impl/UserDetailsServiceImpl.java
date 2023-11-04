package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ysq.domain.entity.LoginUser;
import com.ysq.domain.entity.User;
import com.ysq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名或邮箱查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //在数据库中查询传入用户名是否存在
        queryWrapper.eq(User::getUserName,username).or().eq(User::getEmail,username);;
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户  如果没查到抛出异常
        if(Objects.isNull(user)){
            //在前端提示用户名或密码错误，防止枚举出用户名
            System.out.println("usernofound");
            throw new RuntimeException("用户不存在");

        }
        //返回用户信息
        // TODO 查询权限信息封装
        return new LoginUser(user);
    }
}