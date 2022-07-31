package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Admins;
import com.cn.shangmihsangcheng.service.AdminsService;
import com.cn.shangmihsangcheng.mapper.AdminsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【admins(管理员)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins>
    implements AdminsService{

}




