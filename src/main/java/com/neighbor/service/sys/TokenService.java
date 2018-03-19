package com.neighbor.service.sys;

import com.neighbor.module.sys.TokenModule;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {


    /**
     * 创建登录凭证
     * @return
     */
    public TokenModule createToken(String id);

    public boolean checkToken(String token);
}
