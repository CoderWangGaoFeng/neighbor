package com.neighbor.module.sys;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * shiro安全管理系统权限实体类
 * @author wgf
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name="sys_permission")
public class PermissionModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    /**
     * 权限名字
     */
    private String name;

    /**
     * 资源类型
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限字符串
     * menu例子：role:*
     * button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 父编号列表
     */
    private String parentIds;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

}
