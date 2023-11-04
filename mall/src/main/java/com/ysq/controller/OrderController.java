package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.OrderService;
import com.ysq.vo.OrderSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
//    新增订单
    @PostMapping("/add")
    public ResponseResult addOder (@RequestBody OrderSaveVo orderSaveVo){
        return orderService.addOder(orderSaveVo);
    }
//    获取个人订单
    @GetMapping("/personOrder")
    public ResponseResult personOrder (@RequestParam(value = "pageNum") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize){
        return orderService.personOrder(pageNum,pageSize);
    }
}
