package com.ysq.service.impl;

import com.alibaba.excel.event.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.*;
import com.ysq.mapper.SysOrderMapper;
import com.ysq.service.GoodsSkuService;
import com.ysq.service.OrderItemService;
import com.ysq.service.OrderService;
import com.ysq.service.ShopCartService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.SecurityUtils;
import com.ysq.vo.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2023-10-05 22:08:55
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements OrderService {
    @Autowired
    GoodsSkuService goodsSkuService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ShopCartService shopCartService;

    @Override
    @Transactional
    public ResponseResult addOder(OrderSaveVo orderSaveVo) {
        List<OrderItem> orderItemList = orderSaveVo.getOrderItemList();
        if (orderItemList.size() == 0) {
            return ResponseResult.errorResult(500, "库存不足");
        }
        SysOrder order = new SysOrder();
        //判断库存
        for (OrderItem item : orderItemList) {
            Long goodsSkuId = item.getGoodsSkuId();
            GoodsSku goodsSku = goodsSkuService.getById(goodsSkuId);
            if (goodsSku.getGoodsStock() < item.getQuantity()) {
                return ResponseResult.errorResult(500, "库存不足");
            }
        }
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        order.setUid(userId);
        order.setRecvName(orderSaveVo.getRecvName());
        order.setRecvPhone(orderSaveVo.getRecvPhone());
        order.setRecvAddress(orderSaveVo.getRecvAddress());
        Double totalPrice = 0d;
        //计算价格
        for (OrderItem item : orderItemList) {
            Long goodsSkuId = item.getGoodsSkuId();
            GoodsSku goodsSku = goodsSkuService.getById(goodsSkuId);
            totalPrice += goodsSku.getGoodsPrice() * item.getQuantity();
            item.setPrice(goodsSku.getGoodsPrice());
        }
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        save(order);
        for (OrderItem item : orderItemList) {
            item.setOrderId(order.getId());
        }
        orderItemService.saveBatch(orderItemList);
        //修改库存
        for (OrderItem item : orderItemList) {
            GoodsSku sku = goodsSkuService.getById(item.getGoodsSkuId());
            LambdaUpdateWrapper<GoodsSku> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(GoodsSku::getGoodsStock, sku.getGoodsStock() - item.getQuantity());
            updateWrapper.eq(GoodsSku::getId, item.getGoodsSkuId());
            goodsSkuService.update(updateWrapper);
        }
        //删除购物车
        for (OrderItem item : orderItemList) {
            LambdaQueryWrapper<ShopCart> shopCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
            shopCartLambdaQueryWrapper.eq(ShopCart::getUserId, userId);
            shopCartLambdaQueryWrapper.eq(ShopCart::getGoodsSkuId, item.getGoodsSkuId());
            shopCartService.remove(shopCartLambdaQueryWrapper);
        }
        return ResponseResult.okResult();
    }

//    @Override
//    public ResponseResult personOrder() {
//        //获取当前用户id
//        Long userId = SecurityUtils.getUserId();
//        LambdaQueryWrapper<SysOrder> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        orderLambdaQueryWrapper.eq(SysOrder::getUid, userId);
//        orderLambdaQueryWrapper.orderByDesc(SysOrder::getId);
//        List<PersonOrderVo> personOrderVoList = BeanCopyUtils.copyBeanList(list(orderLambdaQueryWrapper), PersonOrderVo.class);
//        for (PersonOrderVo item : personOrderVoList) {
//            LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            orderItemLambdaQueryWrapper.eq(OrderItem::getOrderId, item.getId());
//            List<OrderItem> orderItemList = orderItemService.list(orderItemLambdaQueryWrapper);
//            item.setOrderItemList(orderItemList);
//        }
//        return ResponseResult.okResult(personOrderVoList);
//    }

    @Override
    public ResponseResult personOrder(Integer pageNum, Integer pageSize) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<SysOrder> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(SysOrder::getUid, userId);
        orderLambdaQueryWrapper.orderByDesc(SysOrder::getId);
        Page<SysOrder> page = new Page<>(pageNum, pageSize);
        page(page, orderLambdaQueryWrapper);
        List<PersonOrderVo> personOrderVoList = BeanCopyUtils.copyBeanList(page.getRecords(), PersonOrderVo.class);
        for (PersonOrderVo item : personOrderVoList) {
            LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderItemLambdaQueryWrapper.eq(OrderItem::getOrderId, item.getId());
            List<OrderItem> orderItemList = orderItemService.list(orderItemLambdaQueryWrapper);
            item.setOrderItemList(orderItemList);
        }
        PageVo pageVo = new PageVo(personOrderVoList, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
    @Override
    public ResponseResult orderList(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysOrder> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.orderByDesc(SysOrder::getId);
        Page<SysOrder> page = new Page<>(pageNum, pageSize);
        page(page, orderLambdaQueryWrapper);
        List<PersonOrderVo> personOrderVoList = BeanCopyUtils.copyBeanList(page.getRecords(), PersonOrderVo.class);
        for (PersonOrderVo item : personOrderVoList) {
            LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderItemLambdaQueryWrapper.eq(OrderItem::getOrderId, item.getId());
            List<OrderItem> orderItemList = orderItemService.list(orderItemLambdaQueryWrapper);
            item.setOrderItemList(orderItemList);
        }
        PageVo pageVo = new PageVo(personOrderVoList, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult serchList(OrderGetListParamsVo paramsVo) {
        LambdaQueryWrapper<SysOrder> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (paramsVo.getStart() != null && paramsVo.getEnd() != null) {
            orderLambdaQueryWrapper.between(SysOrder::getOrderTime, paramsVo.getStart(), paramsVo.getEnd());
        } else if (paramsVo.getOrderId() != null) {
            orderLambdaQueryWrapper.eq(SysOrder::getId, paramsVo.getOrderId());
        }
        orderLambdaQueryWrapper.orderByDesc(SysOrder::getId);
        Page<SysOrder> page = new Page<>(paramsVo.getPageNum(), paramsVo.getPageSize());
        page(page, orderLambdaQueryWrapper);
        List<PersonOrderVo> personOrderVoList = BeanCopyUtils.copyBeanList(page.getRecords(), PersonOrderVo.class);
        for (PersonOrderVo item : personOrderVoList) {
            LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderItemLambdaQueryWrapper.eq(OrderItem::getOrderId, item.getId());
            List<OrderItem> orderItemList = orderItemService.list(orderItemLambdaQueryWrapper);
            item.setOrderItemList(orderItemList);
        }
        PageVo pageVo = new PageVo(personOrderVoList, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

//    月销售额图表数据
    @Override
    public ResponseResult getLineChart() {
        SysOrderMapper mapper = getBaseMapper();
        List<Chart> lineChart = mapper.getLineChart();
        return ResponseResult.okResult(lineChart);
    }
    //    日销售额数据
    @Override
    public ResponseResult getDailySales() {
        SysOrderMapper mapper = getBaseMapper();
        Double dailySales = mapper.getDailySales();
        if(dailySales==null) dailySales=0d;
        return ResponseResult.okResult(dailySales);
    }

}

