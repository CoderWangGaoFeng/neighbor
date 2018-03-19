package com.neighbor.repository.sys;

import com.neighbor.module.sys.AccountModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 用户持久层
 * @author wanggaofeng
 */
@Transactional//事务控制（异常回滚）
@Repository
public interface AccountRepository extends CrudRepository<AccountModule, Serializable>{

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    public AccountModule findByUserName(String userName);
}
