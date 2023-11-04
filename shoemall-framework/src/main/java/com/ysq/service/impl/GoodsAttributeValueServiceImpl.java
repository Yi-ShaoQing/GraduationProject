package com.ysq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.entity.GoodsAttributeValue;
import com.ysq.mapper.GoodsAttributeValueMapper;
import com.ysq.service.GoodsAttributeValueService;
import org.springframework.stereotype.Service;

/**
 * (GoodsAttributeValue)表服务实现类
 *
 * @author makejava
 * @since 2023-09-26 02:37:07
 */
@Service("goodsAttributeValueService")
public class GoodsAttributeValueServiceImpl extends ServiceImpl<GoodsAttributeValueMapper, GoodsAttributeValue> implements GoodsAttributeValueService {

}

