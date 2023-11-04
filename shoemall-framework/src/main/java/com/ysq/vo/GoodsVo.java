package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    private Long id;

    private Long brandId;
    //分类外键
    private Long categoryId;
    //商品图片
    private String pic;

    private String name;
    //商品介绍
    private String description;
}
