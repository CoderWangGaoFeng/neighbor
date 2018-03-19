package com.neighbor.shiro;

import com.neighbor.module.sys.TokenModule;
import com.neighbor.repository.sys.TokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * shiro请求校验。继承OncePerRequestFilter类。保证每次请求
 * 只会调用该类中的重写方法一次
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取请求token，如果token存在，获取用户登陆信息，除去登录url
        String token = getRequestToken(request);
        if(!StringUtils.isEmpty(token)) {
            //查询token信息
            TokenModule tokenEntity = this.tokenRepository.findByToken(token);
//            LoginToken loginToken = tokenService.queryByToken(token);
            if (tokenEntity != null && tokenEntity.getDeadline().getTime() > System.currentTimeMillis()) {
                //设置loginId到request里，后续根据loginId，获取用户登陆信息
                request.setAttribute("loginId", tokenEntity.getAccountId());
                ShiroUtils.setLogin(tokenEntity);
//                ShiroUtils.setLogin(loginService.selectById(loginToken.getCustomerLoginId()));
//                log.info("Current Login User:{}", CurrentUserUtils.getLogin());
            }
        }
        super.doFilter(request,response,filterChain);
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}
