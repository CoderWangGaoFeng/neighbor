package com.neighbor.module.sys;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * shiro权限控制---角色实体类
 * @author wgf
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name="sys_role")
public class RoleModule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 角色描述
     */
    private String desciption;

    /**
     * 角色状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 角色--权限对应
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="sys_role_permission",
        joinColumns = {@JoinColumn(name="role_id")},
        inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private List<PermissionModel> permissions;
}
