package com.ysq.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//此注解的作用为在调用set方法时会返回当前的对象
@Accessors(chain = true)
public class ShopCartVo {

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
    //商品介绍
    private String description;
    private String goodsSkuAttribute;
    private String pic;

}