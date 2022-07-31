package com.cn.shangmihsangcheng.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.shangmihsangcheng.domain.Types;
import com.cn.shangmihsangcheng.service.TypesService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TypeController {

    @Resource
    private TypesService typesService;

    @GetMapping(value = "/typeList")
    @RequiresRoles(value = {"admin"})
    public ModelAndView toType(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/type_list.jsp");
        List<Types> list = typesService.list();
        modelAndView.addObject("typeList",list);
        modelAndView.addObject("flag",1);
        return modelAndView;
    }

    @GetMapping(value = "/typeEdit")
    @RequiresRoles(value = {"admin"})
    public ModelAndView typeEdit(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Types types = typesService.list(new LambdaQueryWrapper<Types>().eq(Types::getId, id)).get(0);
        modelAndView.addObject("type",types);
        modelAndView.setViewName("/admin/type_edit.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/typeUpdate")
    @RequiresRoles(value = {"admin"})
    public ModelAndView typeUpdate(Types types){
        typesService.saveOrUpdate(types);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/type_list.jsp");
        List<Types> list = typesService.list();
        modelAndView.addObject("typeList",list);
        modelAndView.addObject("flag",1);
        return modelAndView;
    }

    @GetMapping(value = "/typeDelete")
    @RequiresRoles(value = {"admin"})
    public ModelAndView typeDelete(Integer id ){
        typesService.removeById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/type_list.jsp");
        List<Types> list = typesService.list();
        modelAndView.addObject("typeList",list);
        modelAndView.addObject("flag",1);
        return modelAndView;
    }


    @PostMapping(value = "/typeSave")
    @RequiresRoles(value = {"admin"})
    public ModelAndView typeSave(Types types){
        ModelAndView modelAndView=new ModelAndView();
        typesService.saveOrUpdate(types);
        List<Types> list = typesService.list();
        modelAndView.addObject("typeList",list);
        modelAndView.addObject("flag",1);
        modelAndView.setViewName("/admin/type_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/redirect/type_add")
    @RequiresRoles(value = {"admin"})
    public ModelAndView totypeAdd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/type_add.jsp");
        return modelAndView;
    }

}
