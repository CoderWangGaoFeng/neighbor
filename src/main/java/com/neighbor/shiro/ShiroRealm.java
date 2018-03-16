package com.neighbor.shiro;

import com.neighbor.model.sys.AccountModel;
import com.neighbor.model.sys.PermissionModel;
import com.neighbor.model.sys.RoleModel;
import com.neighbor.repository.account.AccountRepository;
import com.neighbor.repository.account.PermissionRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AccountModel user = accountRepository.findByUserName(
                (String)principalCollection.getPrimaryPrincipal());

        //把principals放session中key=userId,value=principals
        SecurityUtils.getSubject().getSession().setAttribute(
                String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        for(RoleModel userRole:user.getRoleList()){
            info.addRole(userRole.getRole());
        }
        //赋予权限
        for(PermissionModel permission : permissionRepository.findPermissionByAccountId(user.getId())){
                info.addStringPermission(permission.getName());
        }

        return info;
    }

    /**
     * 身份认证：Authentication是用来验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证：");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();

        AccountModel user = accountRepository.findByUserName(token.getUsername());
        if (user != null) {
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            //设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            return new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        } else {
            return null;
        }
    }
}
