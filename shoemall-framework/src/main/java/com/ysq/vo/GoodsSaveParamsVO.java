package com.ysq.vo;

import com.ysq.domain.entity.GoodsAttributeValue;
import com.ysq.domain.entity.GoodsSku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSaveParamsVO {

    private Long brandId;
    //分类外键
    private Long categoryId;
    //商品图片
    private String pic;

    private String name;
    //商品介绍
    private String description;
    private List<GoodsAttributeValue> productAttributeValueList;
    private List<GoodsSkuPicParamsVo> productSkuPic;
    private List<GoodsSku> skuStockList;
}
