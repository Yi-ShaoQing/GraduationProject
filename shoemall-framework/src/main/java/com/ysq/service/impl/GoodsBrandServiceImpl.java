package com.ysq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsBrand;
import com.ysq.mapper.GoodsBrandMapper;
import com.ysq.service.GoodsBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格表(GoodsBrand)表服务实现类
 *
 * @author makejava
 * @since 2023-09-19 23:26:00
 */
@Service()
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand> implements GoodsBrandService {

    @Override
    public ResponseResult goodsCategoryList() {
        List<GoodsBrand> list = list();
        return ResponseResult.okResult(list);
    }
}

