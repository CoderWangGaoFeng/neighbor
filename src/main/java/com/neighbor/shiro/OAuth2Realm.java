package com.neighbor.shiro;

import com.neighbor.module.account.enums.AccountStatus;
import com.neighbor.module.sys.AccountModule;
import com.neighbor.module.sys.TokenModule;
import com.neighbor.repository.sys.AccountRepository;
import com.neighbor.repository.sys.TokenRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Realm extends AuthorizingRealm {
//    @Autowired
//    private ShiroService shiroService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyAuthenticationToken;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountModule user = (AccountModule)principals.getPrimaryPrincipal();
//        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        String userId = user.getId();

        //用户权限列表
//        Set<String> permsSet = shiroService.getUserPermissions(userId);
//
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        TokenModule tokenEnity = this.tokenRepository.findByToken(accessToken);
//        SysUserToken tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEnity == null || tokenEnity.getDeadline().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        AccountModule user = this.accountRepository.findById(tokenEnity.getAccountId()).get();
//        SysUser user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == AccountStatus.ACCOUNT_CLOSURE_FOREVER ||
                user.getStatus() == AccountStatus.ACCOUNT_CLOSURE_TIME){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}