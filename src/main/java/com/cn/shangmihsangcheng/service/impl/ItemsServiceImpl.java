package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Items;
import com.cn.shangmihsangcheng.service.ItemsService;
import com.cn.shangmihsangcheng.mapper.ItemsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【items(订单项)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items>
    implements ItemsService{

    @Resource
    private  ItemsMapper itemsMapper;

    @Override
    public List<Items> selectItemsById(Integer userId) {
        return itemsMapper.selectItemsById(userId);
    }
}




