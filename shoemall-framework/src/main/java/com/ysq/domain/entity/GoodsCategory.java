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
 * (GoodsCategory)表实体类
 *
 * @author makejava
 * @since 2023-09-17 01:46:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategory {
    @TableId
    private Long id;
    //父分类ID
    private Long parentId;
    //分类层级 0: 顶级 1:二级 2:三级
    private Integer categoryLevel;
    //分类名称
    private String name;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createdUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedUser;

}

