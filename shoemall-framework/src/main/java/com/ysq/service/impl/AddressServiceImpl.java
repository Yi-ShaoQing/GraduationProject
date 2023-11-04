package com.ysq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysq.domain.ResponseResult;
import com.ysq.mapper.AddressMapper;
import com.ysq.domain.entity.Address;
import com.ysq.service.AddressService;
import com.ysq.utils.BeanCopyUtils;
import com.ysq.utils.SecurityUtils;
import com.ysq.vo.AddressVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author makejava
 * @since 2023-10-05 22:07:01
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

//    收货地址列表
    @Override
    public ResponseResult addressList() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid,userId);
        List<AddressVo> addressVos = BeanCopyUtils.copyBeanList(list(queryWrapper), AddressVo.class);

        return ResponseResult.okResult(addressVos);
    }

//    添加收货地址
    @Override
    public ResponseResult addAddress(String address) {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        Address myAddress = new Address();
        myAddress.setAddress(address);
        myAddress.setUid(userId);
        save(myAddress);
        return addressList();
    }

//    根据id删除收货地址
    @Override
    public ResponseResult deleteAddress(long id) {
        removeById(id);
        return addressList();
    }
}

