package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsBrand;

/**
 * 规格表(GoodsBrand)表服务接口
 *
 * @author makejava
 * @since 2023-09-19 23:12:24
 */
public interface GoodsBrandService extends IService<GoodsBrand> {

    ResponseResult goodsCategoryList();
}

