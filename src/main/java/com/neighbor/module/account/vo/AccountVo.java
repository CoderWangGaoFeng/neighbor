package com.neighbor.module.account.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户返回vo类
 */
@Data
@Accessors(chain=true)
public class AccountVo implements Serializable{

    /**
     * token返回值
     */
    private String token;
}
