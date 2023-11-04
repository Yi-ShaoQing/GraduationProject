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
 * (GoodsAttributeValue)表实体类
 *
 * @author makejava
 * @since 2023-09-25 00:45:51
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAttributeValue {
    @TableId
    private Long id;
    //商品外键
    private Long goodsId;
    //属性外键
    private Long attributeId;
    //属性value值
    private String attributeName;
    private String attributeValue;
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

