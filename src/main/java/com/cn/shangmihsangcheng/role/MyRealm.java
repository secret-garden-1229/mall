package com.cn.shangmihsangcheng.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.shangmihsangcheng.domain.Admins;
import com.cn.shangmihsangcheng.domain.Users;
import com.cn.shangmihsangcheng.service.AdminsService;
import com.cn.shangmihsangcheng.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UsersService usersService;

    @Resource
    private AdminsService adminsService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Set<String> role=new HashSet<>();
        if (primaryPrincipal instanceof Users){
            role.add("user");
        }

        if (primaryPrincipal instanceof Admins){
            role.add("user");
            role.add("admin");
        }

        simpleAuthorizationInfo.setRoles(role);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken toke = (UsernamePasswordToken) authenticationToken;
        String username=toke.getUsername();
        String password= String.valueOf(toke.getPassword());
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        List<Users> list = usersService.list(queryWrapper);
        if (list.isEmpty()&&list.size()!=1){
            List<Admins> list1 = adminsService.list(queryWrapper);
            if (list1.size()!=0){
                return   new  SimpleAuthenticationInfo(list1.get(0),list1.get(0).getPassword(),this.getName());
            }
           throw new AuthenticationException("请确定账号，密码正确");
        }
        return   new  SimpleAuthenticationInfo(list.get(0),list.get(0).getPassword(),this.getName());
    }
}
