package com.ysq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysq.domain.ResponseResult;
import com.ysq.domain.entity.Address;

/**
 * (Address)表服务接口
 *
 * @author makejava
 * @since 2023-10-05 22:06:42
 */
public interface AddressService extends IService<Address> {

    ResponseResult addressList();

    ResponseResult addAddress(String address);

    ResponseResult deleteAddress(long id);
}

