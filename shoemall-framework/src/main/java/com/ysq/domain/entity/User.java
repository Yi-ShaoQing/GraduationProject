package com.ysq.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2023-08-21 00:11:40
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User {
    //主键
    @TableId
    private Long id;
    //用户名
    private String userName;
    //密码
    private String password;
    //手机号
    private String phonenumber;
    //邮箱
    private String email;
    //用户性别（0男，1女，2未知）
    private String sex;
    //生日
    private Date birthday;
    //头像
    private String avatar;
    //用户类型（0管理员，1普通用户）
    private String userType;
    //账号状态（0正常 1停用）
    private String status;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

}

