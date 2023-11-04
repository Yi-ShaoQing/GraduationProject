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
 * (ShopCart)表实体类
 *
 * @author makejava
 * @since 2023-09-06 16:40:33
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    @TableId
    private Long id;
    //用户id
    private Long userId;
    //商品id
    private Long goodsId;
    
    private Long goodsSkuId;
    //数量
    private Integer quantity;
    
    private Double price;
    //是否选择
    private Integer isSelect;
    
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

}

