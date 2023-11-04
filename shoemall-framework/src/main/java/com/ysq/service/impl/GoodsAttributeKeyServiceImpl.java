package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsAttributeKey;
import com.ysq.domain.entity.GoodsCategory;
import com.ysq.mapper.GoodsAttributeKeyMapper;
import com.ysq.service.GoodsAttributeKeyService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.vo.GoodsAttributeKeyVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (GoodsAttributeKey)表服务实现类
 *
 * @author makejava
 * @since 2023-09-22 00:24:14
 */
@Service("goodsAttributeKeyService")
public class GoodsAttributeKeyServiceImpl extends ServiceImpl<GoodsAttributeKeyMapper, GoodsAttributeKey> implements GoodsAttributeKeyService {

    @Override
    public ResponseResult attributeList(long attributeCategoryId) {
        LambdaQueryWrapper<GoodsAttributeKey> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsAttributeKey::getAttributeCategoryId,attributeCategoryId);
        List<GoodsAttributeKey> attributeKeys = list(lambdaQueryWrapper);
        List<GoodsAttributeKeyVo> goodsAttributeKeyVos = BeanCopyUtils.copyBeanList(attributeKeys, GoodsAttributeKeyVo.class);
        return ResponseResult.okResult(goodsAttributeKeyVos);
    }
}

