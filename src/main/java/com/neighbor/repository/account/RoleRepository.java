package com.neighbor.repository.account;

import com.neighbor.model.sys.RoleModel;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * 角色逻辑层
 * @author wgf
 */
public interface RoleRepository extends CrudRepository<RoleModel ,Serializable>{
}
