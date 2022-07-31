package com.cn.shangmihsangcheng.mapper;

import com.cn.shangmihsangcheng.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【orders(订单)】的数据库操作Mapper
* @createDate 2022-07-23 16:17:11
* @Entity com.cn.shangmihsangcheng.domain.Orders
*/
public interface OrdersMapper extends BaseMapper<Orders> {

   List<Orders> selectOrderById(Integer userId);

   List<Orders> selectOrderByUserId(Integer userId);

   List<Orders> selectNewOrderById(Integer userId);
   List<Orders> selectorderinfo(Integer status);

}




