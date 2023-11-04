package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryTreeVo {
    private Long id;
    //父分类ID
    //分类层级 0: 顶级 1:二级 2:三级
    private Integer categoryLevel;
    //分类名称
    private String name;
    private List<GoodsCategoryTreeVo> children;

}
