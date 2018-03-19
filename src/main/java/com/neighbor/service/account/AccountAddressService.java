package com.neighbor.service.account;

import com.neighbor.module.ResponseObject;
import com.neighbor.module.account.AccountAddressModule;

/**
 * 用户地址逻辑层
 */
public interface AccountAddressService {

    public ResponseObject insert(AccountAddressModule address) throws Exception;
}
