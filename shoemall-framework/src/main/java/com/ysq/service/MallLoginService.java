package com.ysq.service;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.User;

public interface MallLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
