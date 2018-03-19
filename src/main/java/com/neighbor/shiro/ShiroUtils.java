package com.neighbor.shiro;

import com.neighbor.module.sys.TokenModule;

/**
 * 请求校验辅助类
 * 登录的用户，发起请求时。在AuthFilter中校验token，
 * 并查询相关用户数据。并保存在ThiredLocal中
 */
public class ShiroUtils {
    private static final ThreadLocal<TokenModule> loginThreadLocal = new InheritableThreadLocal<>();

    public static final String LOGIN_ID = "loginId";

    public static final String HEADER_TOKEN_KEY = "token";

    public static TokenModule getLogin() {

        return loginThreadLocal.get();
    }

    public static void setLogin(TokenModule entity) {
        loginThreadLocal.set(entity);
    }

    public static void removeLogin(){
        loginThreadLocal.remove();
    }
}
