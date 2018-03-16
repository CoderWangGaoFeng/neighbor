package com.neighbor.model.account;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 用户信息实体类
 * @author wanggaofeng
 */
@Entity
@Data
@Table(name="table_account_address")
public class AccountAddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 小区名称
     */
    private String village;

    /**
     * 楼号
     */
    private String floorNumber;

    /**
     * 单元号
     */
    private String unitNumber;

    /**
     * 房号
     */
    private String homeNumber;

    /**
     * 外键
     */
//    @OneToMany(targetEntity = AccountModel.class)
//    @JoinTable(name = "account_accountinfo",
//        joinColumns = {@JoinColumn(name="id")},
//        inverseJoinColumns = {@JoinColumn(name = "account_id")})
//    private Set<AccountModel> account;

    /**
     * 填写时间
     */
    private Timestamp time;
}
