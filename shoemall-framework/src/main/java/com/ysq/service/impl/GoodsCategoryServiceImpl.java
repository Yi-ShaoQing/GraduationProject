package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.GoodsCategory;
import com.ysq.mapper.GoodsCategoryMapper;
import com.ysq.service.GoodsCategoryService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.vo.GoodsCategoryPageVo;
import com.ysq.vo.GoodsCategoryTreeVo;
import com.ysq.vo.GoodsCategoryVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (GoodsCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-09-17 02:39:42
 */
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

    private Page<GoodsCategory> page;

    @Override
    public ResponseResult goodsCategoryList(long parentId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<GoodsCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsCategory::getParentId,parentId);
//        lambdaQueryWrapper.orderByAsc(GoodsCategory::getId);
        Page<GoodsCategory> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<GoodsCategoryVo> goodsCategoryVos = BeanCopyUtils.copyBeanList(page.getRecords(), GoodsCategoryVo.class);
        for (GoodsCategoryVo item : goodsCategoryVos){
            int size = categoryChildenList(item.getId()).size();
            if (size>0){
                item.setIsHaveChildren(true);
            }else {
                item.setIsHaveChildren(false);
            }
        }
        GoodsCategoryPageVo goodsCategoryPageVo = new GoodsCategoryPageVo(goodsCategoryVos,page.getTotal());
        return ResponseResult.okResult(goodsCategoryPageVo);
    }

    @Override
    public List<GoodsCategory> categoryChildenList(long parentId) {
        LambdaQueryWrapper<GoodsCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsCategory::getParentId,parentId);
        List<GoodsCategory> childenList = list(lambdaQueryWrapper);
        return childenList;
    }

    @Override
    public ResponseResult goodsCategoryTree() {
        GoodsCategoryTreeVo goodsCategoryTreeVo = new GoodsCategoryTreeVo();
        goodsCategoryTreeVo.setId(0L);
        goodsCategoryTreeVo.setName("无上级分类");
        goodsCategoryTreeVo.setCategoryLevel(0);
        goodsCategoryTreeVo.setChildren(goodsCategoryTreeChilen(0l));
        return ResponseResult.okResult(goodsCategoryTreeVo);
    }

    @Override
    public ResponseResult createGoodsCategory(GoodsCategory goodsCategory) {
        boolean save = save(goodsCategory);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getGoodsCategoryById(long id) {
        LambdaQueryWrapper<GoodsCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsCategory::getId,id);
        GoodsCategory goodsCategory = getOne(lambdaQueryWrapper);
        GoodsCategoryVo goodsCategoryVo = BeanCopyUtils.copyBean(goodsCategory, GoodsCategoryVo.class);
        return ResponseResult.okResult(goodsCategoryVo);
    }

    @Override
    public ResponseResult updateGoodsCategory(GoodsCategory goodsCategory) {
        updateById(goodsCategory);
        return ResponseResult.okResult();
    }

    @Override
//    不包含无上级分类
    public ResponseResult goodsCategoryWithChildren() {
        LambdaQueryWrapper<GoodsCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsCategory::getParentId,0);
        List<GoodsCategory> list = list(lambdaQueryWrapper);
        List<GoodsCategoryTreeVo> treeVos = BeanCopyUtils.copyBeanList(list, GoodsCategoryTreeVo.class);
        for(GoodsCategoryTreeVo item : treeVos){
            item.setChildren(goodsCategoryTreeChilen(item.getId()));
        }
        return ResponseResult.okResult(treeVos);
    }

    public List<GoodsCategoryTreeVo> goodsCategoryTreeChilen(long parentId){
        List<GoodsCategory> goodsCategories = categoryChildenList(parentId);
        List<GoodsCategoryTreeVo> childenList = new ArrayList<>();
        if (goodsCategories.size()>0){
            for (GoodsCategory item:goodsCategories){
                GoodsCategoryTreeVo treeVo = BeanCopyUtils.copyBean(item, GoodsCategoryTreeVo.class);
                childenList.add(treeVo);
                treeVo.setChildren(goodsCategoryTreeChilen(treeVo.getId()));
            }
        }
        return childenList;
    }
}

