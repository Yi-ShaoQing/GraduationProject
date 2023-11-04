package com.ysq.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (SysOrder)表实体类
 *
 * @author makejava
 * @since 2023-10-06 04:42:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOrder {
    @TableId
    private Long id;
    
    private Long uid;
    
    private String recvName;
    
    private String recvPhone;
    
    private String recvAddress;
    
    private Double totalPrice;
    
    private Integer status;
    
    private Integer delFlag;
    private Date orderTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT)
    private Date modifiedTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;

}

