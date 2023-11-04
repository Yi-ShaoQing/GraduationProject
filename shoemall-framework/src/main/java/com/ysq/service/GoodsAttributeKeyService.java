package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsAttributeKey;

/**
 * (GoodsAttributeKey)表服务接口
 *
 * @author makejava
 * @since 2023-09-22 00:24:14
 */
public interface GoodsAttributeKeyService extends IService<GoodsAttributeKey> {

    ResponseResult attributeList(long attributeCategoryId);
}

