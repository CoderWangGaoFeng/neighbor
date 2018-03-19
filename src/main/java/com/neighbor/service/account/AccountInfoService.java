package com.neighbor.service.account;

import com.neighbor.module.account.AccountAddressModule;

/**
 * 用户信息逻辑层
 * @author wgf
 */
public interface AccountInfoService {

    /**
     * 完善信息
     * @param info
     */
    public void insert(AccountAddressModule info)throws Exception ;
}
