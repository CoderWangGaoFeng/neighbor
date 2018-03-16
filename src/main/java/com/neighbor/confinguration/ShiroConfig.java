package com.neighbor.confinguration;

import com.neighbor.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @author wgf
 */
@Configuration
public class ShiroConfig {

    /**
     * LifecycleBeanPostProcessor,这是个DestructionAwareBeanPostProcessor
     * 的子类，负责org.apache.shiro.util.Initializable类型bean的生命周期，初始化
     * 和销毁。主要是AuthorizaingRealm类的子类，以及EhCacheManager类
     */
    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登录认证的时候，
     * 这个类也负责对form里输入的密码进行编码
     */
    @Bean(name="hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//散列加密方式
        credentialsMatcher.setHashIterations(2);//散列加密次数，次数2表示散列2次，例如：MD5（MD5）
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * shiroRealm,这是个自定义的认证类，继承自AuthorizingRealm,
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现
     */
    public ShiroRealm shiroRealm(){
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * EhCacheManager,缓存管理，用户登录成功后，把用户信息和权限信息
     * 缓存起来，然后每次用户请求时，放入用户的session中，如果不设置
     * 这个bean，每个请求都会查询一次数据库
     */
//    @Bean(name="ehCacheManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhCacheManager ehCacheManager(){
//        return new EhCacheManager();
//    }

    /**
     * SecurityManager,权限管理，这个类组合了登录，
     * 登出，权限，session的处理，是个比较重要的类
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
//        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /**
     * ShiroFilterFactorBean,是个factorbean,为了生成
     * ShiroFilter。它主要保持了三项数据，securityManager,filters,
     * filterChainDefinitionManager.
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String,Filter> filters = new LinkedHashMap<String ,Filter>();
        //配置过滤器，位置不能乱
        //配置退出过滤器，其中的具体退出代码Shiro已经替我们实现了，登出后跳转配置loginUrl
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
//        filters.put("logout",null);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String ,String > filterChainDefinitionManager = new LinkedHashMap<String ,String>();
        filterChainDefinitionManager.put("/logout","logout");
        //用户为ROLE_USER角色可以访问。由用户角色控制用户行为
        filterChainDefinitionManager.put("/user/**","authc,roles[ROLE_USER]");
        filterChainDefinitionManager.put("/events/**","authc,roles[ROLE_AMDIN]");
        //这里为了测试，固定写死的值，也可以从数据库或其他配置中读取，此处是用权限控制
        //filterChainDefinitionManager.put("/user/edit/**","authc,perms[user:edit]");

        //配置不会被拦截的连接，按顺序判断
        filterChainDefinitionManager.put("/**","anon");
        filterChainDefinitionManager.put("/api/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        //配置默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/loginSuccess");

        //配置登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");
        //配置未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }

    /**
     * DefalutAdvisonorAutoProxyCreator,Spring的一个bean，由Advisor决定对
     * 那些类的方法进行aop代理
     */
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * AuthorizationAttributeSourceAdvisor,shiro里实现的advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截
     * 用以下注解的方法
     */
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
