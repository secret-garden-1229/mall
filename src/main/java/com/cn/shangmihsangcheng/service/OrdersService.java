package com.cn.shangmihsangcheng.service;

import com.cn.shangmihsangcheng.domain.Items;
import com.cn.shangmihsangcheng.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【orders(订单)】的数据库操作Service
* @createDate 2022-07-23 16:17:11
*/

public interface OrdersService extends IService<Orders> {
    List<Orders> selectOrderById(Integer userId);
    List<Orders> selectOrderByUserId(Integer userId);

    List<Orders> selectNewOrderById(Integer userId);
    List<Orders> selectorderinfo(Integer status);
}
