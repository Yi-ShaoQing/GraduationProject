package com.ysq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MallLonginVo {
    private String token;
    private  UserInfoVo userInfo;
}
