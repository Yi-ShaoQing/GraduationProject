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
 * (Goods)表实体类
 *
 * @author makejava
 * @since 2023-09-25 00:42:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @TableId
    private Long id;
    
    private Long brandId;
    //分类外键
    private Long categoryId;
    //商品图片
    private String pic;
    
    private String name;
    //商品介绍
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;
    private Integer delFlag;
}

