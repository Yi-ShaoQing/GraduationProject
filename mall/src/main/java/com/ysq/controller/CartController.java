package com.ysq.controller;


import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.ShopCart;
import com.ysq.service.ShopCartService;
import com.ysq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ShopCartService shopCartService;

//    购物车商品列表
    @GetMapping("/cartList")
    public ResponseResult getList() {

        ResponseResult result = shopCartService.cartList();
        return result;
    }

//    修改购物车商品勾选
    @PostMapping("/goodsCheckedChange")
    public ResponseResult checkedChange(@RequestBody ShopCart shopCart) {

        ResponseResult result = shopCartService.checkedChange(shopCart);
        return result;
    }

//    是否全选
    @PostMapping("/checkAllChange")
    public ResponseResult checkAllChange(@RequestBody Map<String, Object> params) {
        ResponseResult result = shopCartService.checkAllChange((Boolean) params.get("isAllChecked"));
        return result;
    }

//    商品数量修改
    @PostMapping("/changQuantity")
    public ResponseResult changQuantity(@RequestBody ShopCart shopCart) {
        if (shopCart.getQuantity() == null || shopCart.getQuantity() < 1) {
            shopCart.setQuantity(1);
        } else if (shopCart.getQuantity() > 10) {
            shopCart.setQuantity(10);
        }
        ResponseResult result = shopCartService.changQuantity(shopCart);
        return result;
    }

//    商品删除
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody List<Long> params) {
        ResponseResult result = shopCartService.deleteCartItem(params);
        return result;
    }

//    加入购物车
    @PostMapping("/addCart")
    public ResponseResult addCart(@RequestBody ShopCart shopCart) {
        return shopCartService.addCart(shopCart);
    }
}
