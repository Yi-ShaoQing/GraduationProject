package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    GoodsBrandService brandService;
    @GetMapping("/list")
    public ResponseResult list (){
        return brandService.goodsCategoryList();
    }
}
