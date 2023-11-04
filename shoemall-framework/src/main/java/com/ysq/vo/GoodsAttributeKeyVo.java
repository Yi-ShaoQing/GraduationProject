package com.ysq.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAttributeKeyVo {
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

}
