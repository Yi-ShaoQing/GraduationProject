package com.ysq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.entity.GoodsAttributeImages;
import com.ysq.mapper.GoodsAttributeImagesMapper;
import com.ysq.service.GoodsAttributeImagesService;
import org.springframework.stereotype.Service;

/**
 * 商品属性关联图片(GoodsAttributeImages)表服务实现类
 *
 * @author makejava
 * @since 2023-09-26 02:36:36
 */
@Service("goodsAttributeImagesService")
public class GoodsAttributeImagesServiceImpl extends ServiceImpl<GoodsAttributeImagesMapper, GoodsAttributeImages> implements GoodsAttributeImagesService {

}

