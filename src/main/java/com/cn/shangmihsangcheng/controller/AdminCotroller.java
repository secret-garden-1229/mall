package com.cn.shangmihsangcheng.controller;

import com.cn.shangmihsangcheng.domain.Admins;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.AdminsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AdminCotroller {

    @Resource
    private AdminsService adminsService;

    @GetMapping(value = "/adminList")
    @RequiresRoles(value = {"admin"})
    public ModelAndView adminList(){
        ModelAndView modelAndView=new ModelAndView();
        List<Admins> list = adminsService.list();
        modelAndView.addObject("adminList",list);
        modelAndView.setViewName("/admin/admin_list.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/admin_reset")
    @RequiresRoles(value = {"admin"})
    public ModelAndView adminReset(Admins admins){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("param",admins);
        modelAndView.setViewName("/admin/admin_reset.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/adminResetPassword")
    @RequiresRoles(value = {"admin"})
    public ModelAndView adminResetPassword(Admins admins){
        ModelAndView modelAndView=new ModelAndView();
        adminsService.saveOrUpdate(admins);
        List<Admins> list = adminsService.list();
        modelAndView.addObject("adminList",list);
        modelAndView.setViewName("/admin/admin_list.jsp");
        return modelAndView;
    }

}
