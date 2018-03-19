package com.neighbor.module.sys;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/***
 * token类，用来做登录限制的
 */
@Entity
@Data
@Table(name="sys_token")
@Accessors(chain = true)
public class TokenModule {

    /**
     * 用户id
     */
    @Id
    private String accountId;

    /**
     * 登录凭证
     */
    private String token;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 过期时间
     */
    private Timestamp deadline;
}
