package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsCategory;
import com.ysq.enums.AppHttpCodeEnum;
import com.ysq.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryContolrler {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/list/{parentId}")
    public ResponseResult list (@PathVariable long parentId,
                                @RequestParam(value = "pageNum") Integer pageNum ,
                                @RequestParam(value = "pageSize") Integer pageSize){
         return goodsCategoryService.goodsCategoryList(parentId,pageNum,pageSize);
    }
    @GetMapping("/withChildren")
    public ResponseResult withChildren (){
        return goodsCategoryService.goodsCategoryWithChildren();
    }
    @GetMapping("/tree")
    public ResponseResult tree (){
        return goodsCategoryService.goodsCategoryTree();
    }
    @PostMapping("/delete/{id}")
    public ResponseResult delete (@PathVariable long id){
        boolean result = goodsCategoryService.removeById(id);
        if (result){
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"删除失败");
        }
    }
    @GetMapping("/{id}")
    public ResponseResult getGoodsCategoryById (@PathVariable long id){
        return goodsCategoryService.getGoodsCategoryById(id);
    }
    @PostMapping("/create")
    public ResponseResult create(@RequestBody GoodsCategory goodsCategory){
        return goodsCategoryService.createGoodsCategory(goodsCategory);
    }
    @PostMapping("/update")
    public ResponseResult update(@RequestBody GoodsCategory goodsCategory){
        return goodsCategoryService.updateGoodsCategory(goodsCategory);
    }
}
