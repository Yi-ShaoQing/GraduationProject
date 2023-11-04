package com.ysq.vo;

import com.ysq.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOrderVo {
    private Long id;

    private Long uid;

    private String recvName;

    private String recvPhone;

    private String recvAddress;

    private Double totalPrice;

    private Integer status;

    private Integer delFlag;

    private Date orderTime;
    private List<OrderItem> orderItemList;
}
