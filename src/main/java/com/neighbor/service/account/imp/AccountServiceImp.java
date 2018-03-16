package com.neighbor.service.account.imp;

import com.neighbor.exception.NeiException;
import com.neighbor.model.ResponseObject;
import com.neighbor.model.sys.AccountModel;
import com.neighbor.repository.account.AccountRepository;
import com.neighbor.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * 用户逻辑层具体实现类
 * @author wanggaofeng
 */
@Service
public class AccountServiceImp  implements AccountService{

    @Autowired
    AccountRepository accountRepository;
    /**
     * 注册
     * @param account
     */
    @Override
    public ResponseObject signUp(AccountModel account)throws Exception{
        Optional.ofNullable(account.getUserName())
                .orElseThrow(()->new NeiException("用户名不能为空"));
        Optional.ofNullable(account.getPassword())
                .map(password->{
                    if(password.length() < 6){
                        throw new NeiException("密码不能少于6位");
                    }
                    account.setUserName(account.getUserName().trim());
                    account.setPassword(password.trim());
                    return password;
                })
                .orElseThrow(()->new NeiException("密码不能为空"));
        AccountModel entity = this.accountRepository.findByUserName(account.getUserName());
        if(entity == null) {
            account.setCreateTime(new Timestamp(System.currentTimeMillis()));
            this.accountRepository.save(account);
            return new ResponseObject().success(null, "注册成功", 200);
        }else{
            throw new NeiException("账户已存在",0);
        }
    }

    /**
     * 完善用户信息
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public ResponseObject setInfo(AccountModel account) throws Exception {
        AccountModel accEntity = this.accountRepository.findByUserName(account.getUserName());

        return null;
    }
}
