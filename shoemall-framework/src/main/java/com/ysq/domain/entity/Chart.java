package com.ysq.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chart {
    //月销量折线图表
    private Integer MONTH;
    private Double sumPrice;
}
