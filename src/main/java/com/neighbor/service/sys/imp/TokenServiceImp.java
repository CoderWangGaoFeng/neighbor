package com.neighbor.service.sys.imp;

import com.neighbor.module.sys.TokenModule;
import com.neighbor.repository.sys.TokenRepository;
import com.neighbor.service.sys.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * token逻辑层
 */
@Service
public class TokenServiceImp implements TokenService{

    @Autowired
    private TokenRepository tokenRepository;
    /**
     * 创建登录凭证
     * @param id
     * @return
     */
    @Override
    public TokenModule createToken(String id) {
        /**
         * 1.根据用户id查询是否存在登录凭证
         * 2.不存在则创建，存在则更新
         */
        TokenModule entity = this.tokenRepository.findById(id)
                .map(tokenEntity ->{
                    String token = UUID.randomUUID().toString();
                    tokenEntity.setToken(token)
                            .setCreateTime(new Timestamp(System.currentTimeMillis()))
                            .setDeadline(new Timestamp(System.currentTimeMillis()+60*60*1000));
                    return this.tokenRepository.save(tokenEntity);
                })
                .orElse( new TokenModule()
                        .setAccountId(id)
                        .setToken(UUID.randomUUID().toString())
                        .setCreateTime(new Timestamp(System.currentTimeMillis())))
                        .setDeadline(new Timestamp(System.currentTimeMillis()+60*60*1000));
        this.tokenRepository.save(entity);
        return entity;
    }

    /**
     * 校验登录状态
     * @return
     */
    @Override
    public boolean checkToken(String token){
        /**
         * 1.通过token查询数据
         * 2.为空判断
         * 3.判断登录是否过期
         */
        TokenModule entity = this.tokenRepository.findByToken(token);
        if(entity == null){
            return false;
        }else if(System.currentTimeMillis() > entity.getDeadline().getTime()){//登录尚在有效期
            return true;
        }else{
            return false;
        }
    }
}
