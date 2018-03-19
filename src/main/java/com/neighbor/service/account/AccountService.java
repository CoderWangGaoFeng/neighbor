package com.neighbor.service.account;

import com.neighbor.module.ResponseObject;
import com.neighbor.module.account.vo.AccountVo;
import com.neighbor.module.sys.AccountModule;

import java.util.Map;

/**
 * 用户逻辑层
 * @author wanggaofeng
 */
public interface AccountService{

    /**
     * 用户注册
     * @param account
     */
    public Map<String ,String> signUp(AccountModule account) throws Exception;

    /**
     * 用户登录
     * @param account
     * @return
     * @throws Exception
     */
    public AccountVo signIn(AccountModule account) throws Exception;

    /**
     * 用户完善头像等基本资料
     * @param account
     * @return
     * @throws Exception
     */
    public ResponseObject setInfo(AccountModule account) throws Exception;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    public AccountModule findByUserName(String userName);
}
