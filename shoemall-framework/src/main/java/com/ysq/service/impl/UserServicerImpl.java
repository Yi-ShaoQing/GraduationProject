package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.ShopCart;
import com.ysq.domain.entity.User;
import com.ysq.enums.AppHttpCodeEnum;
import com.ysq.exception.SystemException;
import com.ysq.mapper.UserMapper;
import com.ysq.service.UserService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.SecurityUtils;
import com.ysq.vo.ShopCartVo;
import com.ysq.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServicerImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResponseResult myGetUser() {
        UserMapper userMapper = getBaseMapper();
        List<User> users = userMapper.selectList(null);
        return ResponseResult.okResult(users);

    }

    //加密工具
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult regiester(User user) {
        //判断用户名是否为空
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        } else if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        } else if (!StringUtils.hasText(user.getPhonenumber())) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_NOT_NULL);
        } else if (!StringUtils.hasText(user.getSex())) {
            throw new SystemException(AppHttpCodeEnum.SEX_NUT_NULL);
        } else if (user.getBirthday() == null) {
            throw new SystemException(AppHttpCodeEnum.BIRTHDAY_NUT_NULL);
        }
        if (userExist(user.getUserName(), user.getEmail())) {
            throw new SystemException((AppHttpCodeEnum.USERNAME_OR_EMAIL_EXIST));
        }
        //加密存储密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(getById(userId), UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateAvatar(String avatar) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getAvatar, avatar);
        updateWrapper.eq(User::getId, userId);
        update(null, updateWrapper);
        return ResponseResult.okResult();
    }

    private boolean userExist(String userName, String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName).or().eq(User::getEmail, email);
        //查询用户名或邮箱是否存在
        return count(queryWrapper) > 0;
    }
}
