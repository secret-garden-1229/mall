package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.UsersService;
import com.cn.shangmihsangcheng.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【users(用户)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




