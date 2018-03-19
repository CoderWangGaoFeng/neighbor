package com.neighbor.module.sys;

import com.neighbor.module.account.enums.AccountStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户实体类
 * @author  WangGaoFeng
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name="sys_account")
public class AccountModule implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid",name="uuid")
    //@GenericGenerator注解需要与@GeneratoedValue注解配合使用，是hibernate
    //用来自定义主键用。其中@GenericGenerator中的name属性必须与@GeneratedVlaue中的generator
    //属性的值一致
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态
     */
    private AccountStatus status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 用户对应的多个角色
     */
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中将对应表中数据查询出来
    @JoinTable(name="sys_account_role",
        joinColumns = {@JoinColumn(name="account_id")},
        inverseJoinColumns = @JoinColumn(name="role_id"))
    //name为中间表；joinColumns和inverseJoinColumn为中间表中的列
    private List<RoleModule> roleList;

}
