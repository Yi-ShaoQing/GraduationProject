package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.User;

public interface UserService extends IService<User> {
    ResponseResult myGetUser();

    ResponseResult regiester(User user);

    ResponseResult getInfo();

    ResponseResult updateAvatar(String avatar);
}
