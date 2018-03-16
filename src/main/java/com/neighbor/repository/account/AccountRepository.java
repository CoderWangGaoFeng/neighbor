package com.neighbor.repository.account;

import com.neighbor.model.sys.AccountModel;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 用户持久层
 * @author wanggaofeng
 */
@Transactional//事务控制（异常回滚）
public interface AccountRepository extends CrudRepository<AccountModel , Serializable>{

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    public AccountModel findByUserName(String userName);
}
