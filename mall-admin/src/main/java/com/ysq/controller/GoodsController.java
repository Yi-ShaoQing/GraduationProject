package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.GoodsAttributeKeyService;
import com.ysq.service.GoodsService;
import com.ysq.vo.GoodsGetListParamsVo;
import com.ysq.vo.GoodsSaveParamsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeKeyService goodsAttributeKeyService;

    @GetMapping("/list")
    public  ResponseResult list (GoodsGetListParamsVo listParamsVo){
        return goodsService.goodsList(listParamsVo);
    }
    @PostMapping("delete/{id}")
    public ResponseResult deleteGoods (@PathVariable Long id){
        return goodsService.deleteGoods(id);
    }
    @PostMapping("/create")
    public ResponseResult create (@RequestBody GoodsSaveParamsVO goodsSaveParamsVO){
        goodsService.create(goodsSaveParamsVO);
        return ResponseResult.okResult();
    }
    @PostMapping("update/{id}")
    public ResponseResult updateGoods (@PathVariable Long id,@RequestBody GoodsSaveParamsVO goodsSaveParamsVO){
        return goodsService.updateGoods(id,goodsSaveParamsVO);
    }
    @GetMapping("/attributeList/{cid}")
    public ResponseResult attributeList (@PathVariable long cid){
        return goodsAttributeKeyService.attributeList(cid);
    }
    @GetMapping("info/{id}")
    public ResponseResult info (@PathVariable long id){
        return goodsService.getInfo(id);
    }
}
