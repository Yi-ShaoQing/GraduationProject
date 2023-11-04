package com.ysq.vo;

import com.ysq.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveVo {
    private String recvName;

    private String recvPhone;

    private String recvAddress;
    private List<OrderItem> orderItemList;
}
