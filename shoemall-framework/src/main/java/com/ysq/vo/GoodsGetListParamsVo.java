package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsGetListParamsVo {
    private Long productId;
    private Long brandId;
    //分类外键
    private Long productCategoryId;
    //商品图片
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
}
