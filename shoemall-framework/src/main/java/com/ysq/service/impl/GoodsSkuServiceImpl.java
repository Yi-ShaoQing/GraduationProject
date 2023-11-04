package com.ysq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.entity.GoodsSku;
import com.ysq.mapper.GoodsSkuMapper;
import com.ysq.service.GoodsSkuService;
import org.springframework.stereotype.Service;

/**
 * 规格表(GoodsSku)表服务实现类
 *
 * @author makejava
 * @since 2023-09-25 03:46:58
 */
@Service("goodsSkuService")
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuMapper, GoodsSku> implements GoodsSkuService {

}

