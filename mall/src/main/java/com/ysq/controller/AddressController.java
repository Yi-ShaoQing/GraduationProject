package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import com.ysq.service.AddressService;
import com.ysq.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

//    收货地址列表
    @GetMapping("/addressList")
    public ResponseResult getList() {
        return addressService.addressList();
    }

    @PostMapping("/addAddress")
    public ResponseResult addAddress(@RequestParam(value = "address") String address) {
        return addressService.addAddress(address);
    }

    @PostMapping("/deleteAddress/{id}")
    public ResponseResult deleteAddress(@PathVariable long id) {
        return addressService.deleteAddress(id);
    }
}
