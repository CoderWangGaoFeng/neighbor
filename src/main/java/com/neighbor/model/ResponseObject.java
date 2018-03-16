package com.neighbor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前后端数据数据交互类
 * @author wanggaofeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    /**
     * 用于返回数据具体类
     */
    private Object data;

    /**
     * 返回提示信息
     */
    private String message;

    /**
     * 返回请求状态
     */
    private Integer status;

    /**
     * 成功时调用
     * @param data
     * @param message
     * @param status
     * @return
     */
    public ResponseObject success(Object data , String message ,Integer status){
        this.data = data;
        this.message = message;
        this.status = status;
        return this;
    }

    /**
     * 失败时调用
     * @param message
     * @param status
     * @return
     */
    public ResponseObject failes(String message ,Integer status){
        this.message = message;
        this.status = status;
        return this;
    }

}
