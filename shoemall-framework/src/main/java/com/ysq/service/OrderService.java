package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.SysOrder;
import com.ysq.vo.OrderGetListParamsVo;
import com.ysq.vo.OrderSaveVo;

import java.util.Date;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2023-10-05 22:08:55
 */
public interface OrderService extends IService<SysOrder> {

    ResponseResult addOder(OrderSaveVo orderSaveVo);


    ResponseResult orderList(Integer pageNum, Integer pageSize);

    ResponseResult serchList(OrderGetListParamsVo paramsVo);

    ResponseResult getLineChart();

    ResponseResult getDailySales();

    ResponseResult personOrder(Integer pageNum, Integer pageSize);
}

