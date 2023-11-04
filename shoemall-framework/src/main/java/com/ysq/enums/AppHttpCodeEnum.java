package com.ysq.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_OR_EMAIL_EXIST(501,"用户名或邮箱已注册"),
     PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    PASSWORD_NOT_NULL(506,"密码不能为空"),
    EMAIL_NOT_NULL(507,"邮箱不能为空"),
    PHONENUMBER_NOT_NULL(508,"手机号码不能为空"),
    SEX_NUT_NULL(509,"性别不能为空"),
    BIRTHDAY_NUT_NULL(510,"生日不能为空"),
    LOGIN_ERROR(505,"用户名或密码错误");
    int code;
    String msg;


    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
