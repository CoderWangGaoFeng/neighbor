package com.neighbor.model.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 用户基本信息实体
 * @author wgf
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name="table_account_info")
public class AccountInfoModel {

    /**
     * 与用户登录帐号id共享
     */
    @Id
    public String accountId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String picture;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 填写时间
     */
    private Timestamp time;
}
