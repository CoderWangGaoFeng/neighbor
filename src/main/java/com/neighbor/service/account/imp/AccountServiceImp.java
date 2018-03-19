package com.neighbor.service.account.imp;

import com.neighbor.exception.NeiException;
import com.neighbor.module.ResponseObject;
import com.neighbor.module.account.enums.AccountStatus;
import com.neighbor.module.account.vo.AccountVo;
import com.neighbor.module.sys.AccountModule;
import com.neighbor.module.sys.TokenModule;
import com.neighbor.repository.sys.AccountRepository;
import com.neighbor.service.account.AccountService;
import com.neighbor.service.sys.TokenService;
import com.neighbor.unitl.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 用户逻辑层具体实现类
 *
 * @author wanggaofeng
 */
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenService tokenService;

    /**
     * 注册
     *
     * @param account
     */
    @Override
    public Map<String, String> signUp(AccountModule account) throws Exception {
            Optional.ofNullable(account.getUserName())
                    .orElseThrow(() -> new NeiException("用户名不能为空"));
            Optional.ofNullable(account.getPassword())
                    .map(pw -> {
                        if (pw.length() < 6) {
                            throw new NeiException("密码不能少于6位");
                        }
                        return pw;
                    })
                    .orElseThrow(() -> new NeiException("密码不能为空"));
            AccountModule entity = this.accountRepository.findByUserName(account.getUserName());
            if (entity == null) {
                account.setCreateTime(new Timestamp(System.currentTimeMillis()));
                account.setStatus(AccountStatus.ACCOUNT_NORMAL);
                this.accountRepository.save(account);
                Map<String, String> map = new HashMap<String, String>();
                map.put("status", "200");
                map.put("message", "注册成功");
                return map;
            } else {
                throw new NeiException("帐号已注册,请直接登录");
            }
    }

    /**
     * 登录
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public AccountVo signIn(AccountModule account) throws Exception {
        Utils.checkString(account.getUserName(),"用户名不能为空");
        Utils.checkString(account.getPassword(),"密码不能为空");
        if(account.getPassword().length() < 6){
            throw new NeiException("密码不符合规则");
        }
        AccountModule entity = this.accountRepository.findByUserName(account.getUserName());
        if(AccountStatus.ACCOUNT_NORMAL.equals(entity.getStatus()) && (entity.getPassword().equals(account.getPassword()))){
            TokenModule token = this.tokenService.createToken(entity.getId());
            return new AccountVo().setToken(token.getToken());
        }else{
            throw new NeiException("帐号或密码错误");
        }
    }

    /**
     * 完善用户信息
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public ResponseObject setInfo(AccountModule account) throws Exception {
        AccountModule accEntity = this.accountRepository.findByUserName(account.getUserName());
        return null;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public AccountModule findByUserName(String userName) {
        return this.accountRepository.findByUserName(userName);
    }
}
