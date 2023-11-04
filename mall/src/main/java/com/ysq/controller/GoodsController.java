package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
//    商品模糊查询
    @PostMapping("/serch")
    public ResponseResult serchGoods (@RequestBody Map<String,Object> params){
        String keyword = (String) params.get("keyword");
        if (keyword==null){
            keyword="";
        }
        return goodsService.serchGoods(keyword);
    }
//    根据商品分类查询
    @PostMapping("/getByCategoryId/{categoryId}")
    public ResponseResult getByCategoryId(@PathVariable long categoryId){
        return goodsService.getByCategoryId(categoryId);
    }
    //获取分类及其包含的商品
    @GetMapping("/categoryGoods/{parentId}")
    public ResponseResult categoryGoods (@PathVariable long parentId){
        return goodsService.categoryGoods(parentId);
    }
//    商品详情
    @GetMapping("info/{id}")
    public ResponseResult info (@PathVariable long id){
        return goodsService.getInfo(id);
    }
}
