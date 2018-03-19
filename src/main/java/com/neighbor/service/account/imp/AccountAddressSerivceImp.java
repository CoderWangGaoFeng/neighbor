package com.neighbor.service.account.imp;

import com.neighbor.exception.NeiException;
import com.neighbor.module.ResponseObject;
import com.neighbor.module.ResponseStatus;
import com.neighbor.module.account.AccountAddressModule;
import com.neighbor.module.sys.TokenModule;
import com.neighbor.repository.account.AccountAddressRepository;
import com.neighbor.service.account.AccountAddressService;
import com.neighbor.shiro.ShiroUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.sql.Timestamp;

/**
 * 用户地址逻辑层
 */
@Service
@AllArgsConstructor
public class AccountAddressSerivceImp implements AccountAddressService{

    private final AccountAddressRepository accountAddressRepository;

    /**
     * 新增用户地址
     * @param address
     * @return
     * @throws Exception
     */
    @Override
    public ResponseObject insert(AccountAddressModule address) throws Exception {
        try{
            TokenModule token = ShiroUtils.getLogin();
            address.setAccountId(token.getAccountId());
            address.setTime(new Timestamp(System.currentTimeMillis()));
            this.accountAddressRepository.save(address);
            return new ResponseObject().success("","保存成功", ResponseStatus.RESPONSE_STATUS_200);
        }catch(Exception e){
            e.printStackTrace();
            throw new NeiException("系统错误，保存失败");
        }
    }
}
