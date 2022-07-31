package com.cn.shangmihsangcheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmihsangcheng.domain.Types;
import com.cn.shangmihsangcheng.service.TypesService;
import com.cn.shangmihsangcheng.mapper.TypesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【types(分类)】的数据库操作Service实现
* @createDate 2022-07-23 16:17:11
*/
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types>
    implements TypesService{

}




