package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsCategory;

import java.util.List;

/**
 * (GoodsCategory)表服务接口
 *
 * @author makejava
 * @since 2023-09-17 02:45:51
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {

    ResponseResult goodsCategoryList(long parentId, Integer pageNum, Integer pageSize);

    List categoryChildenList(long parentId);
    ResponseResult goodsCategoryTree();

    ResponseResult createGoodsCategory(GoodsCategory goodsCategory);

    ResponseResult getGoodsCategoryById(long id);

    ResponseResult updateGoodsCategory(GoodsCategory goodsCategory);

    ResponseResult goodsCategoryWithChildren();
}

