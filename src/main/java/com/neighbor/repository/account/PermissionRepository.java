package com.neighbor.repository.account;

import com.neighbor.model.sys.PermissionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 权限逻辑层
 * @author wgf
 */
public interface PermissionRepository extends CrudRepository<PermissionModel ,Serializable>{

    /**
     * 根据用户id查询用户权限
     * @param account_id
     * @return
     */
    @Query(nativeQuery = true ,
            value="select * from sys_permission c where c.id in " +
                    "(select b.permission_id from sys_role_permission b where b.role_id in " +
                    "(select a.role_id from sys_account_role a where a.account_id = :account_id))")
    public List<PermissionModel> findPermissionByAccountId(@Param("account_id")String account_id);
}
