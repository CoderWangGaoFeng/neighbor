package com.neighbor.repository.account;

import com.neighbor.module.account.AccountAddressModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * 用户地址持久层
 */
public interface AccountAddressRepository extends CrudRepository<AccountAddressModule ,Serializable>{
}
