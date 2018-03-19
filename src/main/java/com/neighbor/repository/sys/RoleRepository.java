package com.neighbor.repository.sys;

import com.neighbor.module.sys.RoleModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * 角色逻辑层
 * @author wgf
 */
public interface RoleRepository extends CrudRepository<RoleModule,Serializable>{
}
