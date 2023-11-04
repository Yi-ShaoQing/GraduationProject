package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.*;
import com.ysq.mapper.GoodsMapper;
import com.ysq.service.*;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (Goods)表服务实现类
 *
 * @author makejava
 * @since 2023-09-25 03:02:27
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsSkuService goodsSkuService;
    @Autowired
    GoodsAttributeImagesService attributeImagesService;
    @Autowired
    GoodsAttributeValueService attributeValueService;
    @Autowired
    GoodsCategoryService goodsCategoryService;
//    开启事物
    @Override
    @Transactional
    public void create(GoodsSaveParamsVO goodsSaveParamsVO) {
        Goods goods = BeanCopyUtils.copyBean(goodsSaveParamsVO, Goods.class);
//        添加商品
        boolean result = save(goods);
        List<GoodsSku> skuStockList = goodsSaveParamsVO.getSkuStockList();
        List<GoodsAttributeValue> attributeValueList = goodsSaveParamsVO.getProductAttributeValueList();
        List<GoodsSkuPicParamsVo> productSkuPic = goodsSaveParamsVO.getProductSkuPic();
        if (result){

            for (GoodsSku item:skuStockList){
                item.setGoodsId(goods.getId());
            }
            for (GoodsAttributeValue item:attributeValueList){
                item.setGoodsId(goods.getId());
            }
            List<GoodsAttributeImages> goodsAttributeImages = new ArrayList<>();
            for (GoodsSkuPicParamsVo item:productSkuPic){
                GoodsAttributeImages temp = new GoodsAttributeImages();
                temp.setGoodsId(goods.getId());
//                图片列表对应的属性名
                temp.setAttributeValue(item.getName());
//                将图片列表转化为逗号分割的字符串
                String images = Arrays.toString(item.getPic());
                images = images.substring(1, images.length() - 1);
                temp.setImages(images);
                goodsAttributeImages.add(temp);
            }
            goodsSkuService.saveBatch(skuStockList);
            attributeValueService.saveBatch(attributeValueList);
            attributeImagesService.saveBatch(goodsAttributeImages);

        }
    }

//    商品查询
    @Override
    public ResponseResult goodsList(GoodsGetListParamsVo listParamsVo) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (listParamsVo.getKeyword()!=null&&!listParamsVo.getKeyword().equals("")){
            lambdaQueryWrapper.and(goodsLambdaQueryWrapper -> goodsLambdaQueryWrapper
                    .like(Goods::getName,listParamsVo.getKeyword())
                    .or().like(Goods::getDescription,listParamsVo.getKeyword()));
        }
        if (listParamsVo.getProductId()!=null&&listParamsVo.getProductId()>0){
            lambdaQueryWrapper.eq(Goods::getId,listParamsVo.getProductId());
        }
        if (listParamsVo.getBrandId()!=null&&listParamsVo.getBrandId()>0){
            lambdaQueryWrapper.eq(Goods::getBrandId,listParamsVo.getBrandId());
        }
        if(listParamsVo.getProductCategoryId()!=null&&listParamsVo.getProductCategoryId()>0){
            lambdaQueryWrapper.eq(Goods::getCategoryId,listParamsVo.getProductCategoryId());
        }
        Page<Goods> page = new Page<>(listParamsVo.getPageNum(),listParamsVo.getPageSize());
        page(page,lambdaQueryWrapper);
        List<GoodsVo> goodsVos = BeanCopyUtils.copyBeanList(page.getRecords(), GoodsVo.class);
        PageVo pageVo = new PageVo(goodsVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    @Transactional
    public ResponseResult deleteGoods(Long id) {
        removeById(id);
        LambdaQueryWrapper<GoodsSku> goodsSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<GoodsAttributeImages> imagesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<GoodsAttributeValue> attributeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsSkuLambdaQueryWrapper.eq(GoodsSku::getGoodsId,id);
        imagesLambdaQueryWrapper.eq(GoodsAttributeImages::getGoodsId,id);
        attributeValueLambdaQueryWrapper.eq(GoodsAttributeValue::getGoodsId,id);
        goodsSkuService.remove(goodsSkuLambdaQueryWrapper);
        attributeImagesService.remove(imagesLambdaQueryWrapper);
        attributeValueService.remove(attributeValueLambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getInfo(long id) {
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.eq(Goods::getId, id);
        GoodsSaveParamsVO paramsVO = BeanCopyUtils.copyBean(getOne(goodsLambdaQueryWrapper), GoodsSaveParamsVO.class);

        LambdaQueryWrapper<GoodsSku> goodsSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsSkuLambdaQueryWrapper.eq(GoodsSku::getGoodsId,id);
        paramsVO.setSkuStockList(goodsSkuService.list(goodsSkuLambdaQueryWrapper));

        LambdaQueryWrapper<GoodsAttributeValue> attributeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attributeValueLambdaQueryWrapper.eq(GoodsAttributeValue::getGoodsId,id);
        paramsVO.setProductAttributeValueList(attributeValueService.list(attributeValueLambdaQueryWrapper));

        LambdaQueryWrapper<GoodsAttributeImages> imagesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imagesLambdaQueryWrapper.eq(GoodsAttributeImages::getGoodsId,id);
        List<GoodsAttributeImages> attributeImagesList = attributeImagesService.list(imagesLambdaQueryWrapper);
        List<GoodsSkuPicParamsVo> goodsSkuPicParamsVoList = new ArrayList<>();
        for (GoodsAttributeImages item:attributeImagesList){
            GoodsSkuPicParamsVo temp = new GoodsSkuPicParamsVo();
            temp.setName(item.getAttributeValue());
            temp.setPic(item.getImages().split(","));
            goodsSkuPicParamsVoList.add(temp);
        }
        paramsVO.setProductSkuPic(goodsSkuPicParamsVoList);

        return ResponseResult.okResult(paramsVO);
    }

    @Override
    @Transactional
    public ResponseResult updateGoods(Long id, GoodsSaveParamsVO goodsSaveParamsVO) {
        deleteGoods(id);
        create(goodsSaveParamsVO);
        return ResponseResult.okResult();
    }

//    关键词模糊查找
    @Override
    public ResponseResult serchGoods(String keyword) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.and(goodsLambdaQueryWrapper -> goodsLambdaQueryWrapper
                .like(Goods::getName,keyword)
                .or().like(Goods::getDescription,keyword));
        List<GoodsCardVo> goodsCardVos = BeanCopyUtils.copyBeanList(list(lambdaQueryWrapper), GoodsCardVo.class);
        for (GoodsCardVo item: goodsCardVos){
            Long id = item.getId();
            LambdaQueryWrapper<GoodsSku> goodsSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            goodsSkuLambdaQueryWrapper.eq(GoodsSku::getGoodsId,id);
            goodsSkuLambdaQueryWrapper.orderByAsc(GoodsSku::getGoodsPrice);
            List<GoodsSku> list = goodsSkuService.list(goodsSkuLambdaQueryWrapper);
            if(list.size()>=1){
                item.setPrice(list.get(0).getGoodsPrice());
            }
        }
        return ResponseResult.okResult(goodsCardVos);
    }
//首页二级分类及其包含的商品
    @Override
    public ResponseResult categoryGoods(long parentId) {
        LambdaQueryWrapper<GoodsCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsCategory::getParentId,parentId);
        //获取二级分类
        List<GoodsCategory> categoryList = goodsCategoryService.list(lambdaQueryWrapper);

        List<CategoryGoodsVo> categoryGoodsVos = new ArrayList<>();
        for (GoodsCategory item:categoryList){
            //拷贝分类商品vo中的分类属性
            CategoryGoodsVo categoryGoodsVo = BeanCopyUtils.copyBean(item, CategoryGoodsVo.class);
            //获取分类下包含商品的cardVo
            LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            goodsLambdaQueryWrapper.eq(Goods::getCategoryId,item.getId());
            goodsLambdaQueryWrapper.last("LIMIT 8");
            List<GoodsCardVo> goodsCardVos = BeanCopyUtils.copyBeanList(list(goodsLambdaQueryWrapper), GoodsCardVo.class);
            if(goodsCardVos.size()>0){
                for (GoodsCardVo goodsCard: goodsCardVos){
                    Long id = goodsCard.getId();
                    LambdaQueryWrapper<GoodsSku> goodsSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    goodsSkuLambdaQueryWrapper.eq(GoodsSku::getGoodsId,id);
                    goodsSkuLambdaQueryWrapper.orderByAsc(GoodsSku::getGoodsPrice);
                    List<GoodsSku> list = goodsSkuService.list(goodsSkuLambdaQueryWrapper);
                    if(list.size()>=1){
                        goodsCard.setPrice(list.get(0).getGoodsPrice());
                    }
                }
                categoryGoodsVo.setGoodsCards(goodsCardVos);
                categoryGoodsVos.add(categoryGoodsVo);
            }
        }
        return ResponseResult.okResult(categoryGoodsVos);
    }

    @Override
    public ResponseResult getByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.eq(Goods::getCategoryId,categoryId);
        List<GoodsCardVo> goodsCardVos = BeanCopyUtils.copyBeanList(list(goodsLambdaQueryWrapper), GoodsCardVo.class);
        for (GoodsCardVo item: goodsCardVos){
            Long id = item.getId();
            LambdaQueryWrapper<GoodsSku> goodsSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            goodsSkuLambdaQueryWrapper.eq(GoodsSku::getGoodsId,id);
            goodsSkuLambdaQueryWrapper.orderByAsc(GoodsSku::getGoodsPrice);
            List<GoodsSku> list = goodsSkuService.list(goodsSkuLambdaQueryWrapper);
            if(list.size()>=1){
                item.setPrice(list.get(0).getGoodsPrice());
            }
        }
        return ResponseResult.okResult(goodsCardVos);
    }
}

