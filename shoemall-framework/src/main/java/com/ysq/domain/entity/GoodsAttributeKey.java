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
 * (GoodsAttributeKey)表实体类
 *
 * @author makejava
 * @since 2023-09-22 00:18:40
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAttributeKey {
    @TableId
    private Long id;
    //分类外键
    private Long attributeCategoryId;
    //属性ke值
    private String attributeName;
    //属性是否关联价格1关联/0不关联
    private Integer isAssociatedPrice;
    //属性是否关联图片1关联/0不关联
    private Integer isAssociatedPic;
    //属性录入方式：0->手工录入；1->从列表中选取
    private Integer inputType;
    //可选值列表，以逗号隔开
    private String inputList;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;

}

