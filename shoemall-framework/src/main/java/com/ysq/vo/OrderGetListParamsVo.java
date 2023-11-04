package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetListParamsVo {
    private Long orderId;
    private Date start;
    private Date end;
    private Integer pageNum;
    private Integer pageSize;
}
