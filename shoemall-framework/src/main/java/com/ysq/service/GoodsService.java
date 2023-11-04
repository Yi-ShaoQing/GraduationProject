package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.Goods;
import com.ysq.vo.GoodsGetListParamsVo;
import com.ysq.vo.GoodsSaveParamsVO;

/**
 * (Goods)表服务接口
 *
 * @author makejava
 * @since 2023-09-25 03:02:27
 */
public interface GoodsService extends IService<Goods> {

    void create(GoodsSaveParamsVO goodsSaveParamsVO);

    ResponseResult goodsList(GoodsGetListParamsVo listParamsVo);

    ResponseResult deleteGoods(Long id);

    ResponseResult getInfo(long id);

    ResponseResult updateGoods(Long id, GoodsSaveParamsVO goodsSaveParamsVO);

    ResponseResult serchGoods(String keyword);

    ResponseResult categoryGoods(long parentId);

    ResponseResult getByCategoryId(Long categoryId);
}

