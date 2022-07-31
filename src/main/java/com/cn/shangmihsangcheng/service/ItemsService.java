package com.cn.shangmihsangcheng.service;

import com.cn.shangmihsangcheng.domain.Items;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【items(订单项)】的数据库操作Service
* @createDate 2022-07-23 16:17:11
*/
public interface ItemsService extends IService<Items> {
    List<Items> selectItemsById(Integer userId);
}
