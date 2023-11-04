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
 * 规格表(GoodsSku)表实体类
 *
 * @author makejava
 * @since 2023-09-25 01:47:43
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSku {
    @TableId
    private Long id;
    //商品外键
    private Long goodsId;
    //商品规格
    private String goodsSpecs;
    //商品价格
    private Double goodsPrice;
    //商品库存
    private Integer goodsStock;
    //属性1
    private String sp1;
    //属性2
    private String sp2;
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

