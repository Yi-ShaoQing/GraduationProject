package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryVo {
    private Long id;
    //父分类ID
    private Long parentId;
    //分类层级 0: 顶级 1:二级 2:三级
    private Integer categoryLevel;
    //分类名称
    private Boolean isHaveChildren;
    private String name;
}
