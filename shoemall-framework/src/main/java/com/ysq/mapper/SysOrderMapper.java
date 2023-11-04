package com.ysq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysq.domain.entity.Chart;
import com.ysq.domain.entity.SysOrder;

import java.util.List;

public interface SysOrderMapper extends BaseMapper<SysOrder> {
    List<Chart> getLineChart();
    Double getDailySales();
}
