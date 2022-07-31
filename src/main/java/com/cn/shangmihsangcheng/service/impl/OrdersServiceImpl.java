package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Items;
import com.cn.shangmihsangcheng.domain.Orders;
import com.cn.shangmihsangcheng.service.OrdersService;
import com.cn.shangmihsangcheng.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【orders(订单)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Resource
    private  OrdersMapper ordersMapper;

    @Override
    public List<Orders> selectOrderById(Integer userId) {
        return ordersMapper.selectOrderById(userId);
    }

    @Override
    public List<Orders> selectOrderByUserId(Integer userId) {
        return ordersMapper.selectOrderByUserId(userId);
    }

    @Override
    public List<Orders> selectNewOrderById(Integer userId) {
        return ordersMapper.selectNewOrderById(userId);
    }

    @Override
    public List<Orders> selectorderinfo(Integer status) {
        return ordersMapper.selectorderinfo(status);
    }
}




