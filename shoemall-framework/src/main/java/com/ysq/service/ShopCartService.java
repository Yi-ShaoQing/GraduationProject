package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.ShopCart;

import java.util.List;

public interface ShopCartService extends IService<ShopCart> {
    ResponseResult cartList();

    ResponseResult checkedChange(ShopCart shopCart);

    ResponseResult checkAllChange(Boolean isAllChecked);

    ResponseResult changQuantity(ShopCart shopCart);

    ResponseResult deleteCartItem(List<Long> deletList);

    ResponseResult addCart(ShopCart shopCart);
}
