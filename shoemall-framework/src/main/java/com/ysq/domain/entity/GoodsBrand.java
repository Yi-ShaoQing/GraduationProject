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
 * 规格表(GoodsBrand)表实体类
 *
 * @author makejava
 * @since 2023-09-19 23:06:30
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsBrand {
    @TableId
    private Long id;
    //商品品牌
    private String brandName;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;

}

