package com.ysq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.entity.OrderItem;
import com.ysq.mapper.OrderItemMapper;
import com.ysq.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * (OrderItem)表服务实现类
 *
 * @author makejava
 * @since 2023-10-05 22:09:15
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}

