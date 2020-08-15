package com.lublog.gateway.realm;

import com.lublog.gateway.domain.PhoneToken;
import com.lublog.po.Role;
import com.lublog.po.User;
import com.lublog.service.RoleService;
import com.lublog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: java类作用描述PhoneRealm
 * @Author: lxy
 * @time: 2019/4/23 16:13
 */
public class VerifyCodeRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权");
        String userPhone = principals.getPrimaryPrincipal().toString();
        Set<String> roles=new HashSet<>();
        User user=userService.queryUserByUserPhone(userPhone);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        if (user==null){
            return info;
        }
        Role role=roleService.queryByRoleId(user.getUserId());
        roles.add(role.getRoleName());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("手机认证");
        PhoneToken token = null;
        // 如果是PhoneToken，则强转，获取phone；否则不处理。
        if(authenticationToken instanceof PhoneToken){
            token = (PhoneToken) authenticationToken;
        }else{
            return null;
        }
        String userPhone = (String) token.getPrincipal();
        User user = userService.queryUserByUserPhone(userPhone);
        if (user == null) {
            System.out.println("手机号不正确");
            throw new UnknownAccountException("手机号不正确");
        }
        return new SimpleAuthenticationInfo(user, userPhone, this.getName());
    }
    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof PhoneToken;
    }
}
