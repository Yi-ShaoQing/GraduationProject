package com.ysq.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.Goods;
import com.ysq.domain.entity.GoodsAttributeImages;
import com.ysq.domain.entity.GoodsSku;
import com.ysq.domain.entity.ShopCart;
import com.ysq.mapper.ShopCartMapper;
import com.ysq.service.GoodsAttributeImagesService;
import com.ysq.service.GoodsService;
import com.ysq.service.GoodsSkuService;
import com.ysq.service.ShopCartService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.SecurityUtils;
import com.ysq.vo.ShopCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (ShopCart)表服务实现类
 *
 * @author makejava
 * @since 2023-09-06 18:42:12
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements ShopCartService {
    @Autowired
    GoodsSkuService goodsSkuService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeImagesService goodsAttributeImagesService;
//    获取购物车商品列表
    @Override
    public ResponseResult cartList() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<ShopCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShopCart::getUserId,userId);
        List<ShopCart> cartList = list(queryWrapper);
        List<ShopCartVo> shopCartVoList = BeanCopyUtils.copyBeanList(cartList, ShopCartVo.class);
        for (ShopCartVo item:shopCartVoList){
            GoodsSku goodsSku = goodsSkuService.getById(item.getGoodsSkuId());
            item.setGoodsSkuAttribute("颜色:"+goodsSku.getSp1()+" 尺码:"+goodsSku.getSp2());
            Goods goods = goodsService.getById(item.getGoodsId());
            item.setDescription(goods.getDescription());
            LambdaQueryWrapper<GoodsAttributeImages> imagesLambdaQueryWrapper = new LambdaQueryWrapper<>();
            imagesLambdaQueryWrapper.eq(GoodsAttributeImages::getGoodsId,item.getGoodsId());
            imagesLambdaQueryWrapper.eq(GoodsAttributeImages::getAttributeValue,goodsSku.getSp1());
            GoodsAttributeImages images = goodsAttributeImagesService.getOne(imagesLambdaQueryWrapper);
            String[] imagesList = images.getImages().split(",");
            item.setPic(imagesList[0]);
        }
        return ResponseResult.okResult(shopCartVoList);
    }

    @Override
    public ResponseResult checkedChange(ShopCart shopCart) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaUpdateWrapper<ShopCart> updateWrapper = new LambdaUpdateWrapper<>();
        if(shopCart.getIsSelect()==1){
            updateWrapper.set(ShopCart::getIsSelect,0);
        }else {
            updateWrapper.set(ShopCart::getIsSelect,1);
        }
        updateWrapper.eq(ShopCart::getUserId,userId).eq(ShopCart::getGoodsSkuId,shopCart.getGoodsSkuId());
        update(null, updateWrapper);

        return cartList();
    }

    @Override
    public ResponseResult checkAllChange(Boolean isAllChecked) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaUpdateWrapper<ShopCart> updateWrapper = new LambdaUpdateWrapper<>();
        if(isAllChecked){
            updateWrapper.set(ShopCart::getIsSelect,1);
        }else {
            updateWrapper.set(ShopCart::getIsSelect,0);
        }
        updateWrapper.eq(ShopCart::getUserId,userId);
        update(null, updateWrapper);
        return cartList();
    }
    @Override
    public ResponseResult changQuantity(ShopCart shopCart) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaUpdateWrapper<ShopCart> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(ShopCart::getQuantity,shopCart.getQuantity());
        updateWrapper.eq(ShopCart::getUserId,userId);
        updateWrapper.eq(ShopCart::getGoodsSkuId,shopCart.getGoodsSkuId());
        update(null, updateWrapper);
        return cartList();
    }

    @Override
    public ResponseResult deleteCartItem(List<Long> deletList) {
        removeByIds(deletList);
        return cartList();
    }

    @Override
    public ResponseResult addCart(ShopCart shopCart) {
        if(shopCart.getQuantity()<1||shopCart.getQuantity()==null){
            shopCart.setQuantity(1);
        }
        if(shopCart.getQuantity()>10){
            shopCart.setQuantity(10);
        }
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        shopCart.setUserId(userId);
        GoodsSku goodsSku = goodsSkuService.getById(shopCart.getGoodsSkuId());
        shopCart.setPrice(goodsSku.getGoodsPrice());
        LambdaUpdateWrapper<ShopCart> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ShopCart::getUserId,userId);
        updateWrapper.eq(ShopCart::getGoodsId,shopCart.getGoodsId());
        updateWrapper.eq(ShopCart::getGoodsSkuId,shopCart.getGoodsSkuId());
        saveOrUpdate(shopCart,updateWrapper);
        return ResponseResult.okResult();
    }
}

