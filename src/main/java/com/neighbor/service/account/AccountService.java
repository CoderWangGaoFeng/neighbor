package com.neighbor.service.account;

import com.neighbor.model.ResponseObject;
import com.neighbor.model.sys.AccountModel;

/**
 * 用户逻辑层
 * @author wanggaofeng
 */
public interface AccountService {

    /**
     * 用户注册
     * @param account
     */
    public ResponseObject signUp(AccountModel account) throws Exception;

    /**
     * 用户完善头像等基本资料
     * @param account
     * @return
     * @throws Exception
     */
    public ResponseObject setInfo(AccountModel account) throws Exception;

}
