package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.OrderService;
import com.ysq.vo.OrderGetListParamsVo;
import com.ysq.vo.OrderSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orderList")
    public ResponseResult personOrder(@RequestParam(value = "pageNum") Integer pageNum,
                                      @RequestParam(value = "pageSize") Integer pageSize) {
        return orderService.orderList(pageNum, pageSize);
    }

    @PostMapping("/serchList")
    public ResponseResult serch(@RequestBody OrderGetListParamsVo paramsVo){
        return orderService.serchList(paramsVo);
    }
    @GetMapping("lineChart")
    public ResponseResult getLineChart(){
        return orderService.getLineChart();
    }
    @GetMapping("dailySales")
    public ResponseResult dailySales(){
        return orderService.getDailySales();
    }
}
