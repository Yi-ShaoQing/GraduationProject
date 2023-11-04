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
 * (OrderItem)表实体类
 *
 * @author makejava
 * @since 2023-10-05 22:03:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @TableId
    private Long id;
    
    private Long orderId;
    
    private Long goodsId;
    
    private Long goodsSkuId;
    
    private String description;
    
    private String attribute;
    
    private String image;
    
    private Double price;
    
    private Integer quantity;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    private Integer delFlag;
}

