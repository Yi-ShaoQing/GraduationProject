package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGoodsVo {
    private Long id;
    private String name;
    private List<GoodsCardVo> goodsCards;
}
