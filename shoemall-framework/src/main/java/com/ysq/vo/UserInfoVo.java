package com.ysq.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//此注解的作用为在调用set方法时会返回当前的对象
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;
    //用户名
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;
    //手机号
    private String phonenumber;

}