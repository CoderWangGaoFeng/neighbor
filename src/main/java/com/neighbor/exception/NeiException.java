package com.neighbor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * 自定义异常
 * @author wgf
 * 可以通过@ResponseStatus注解配合修改返回异常的状态码
 */
//@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NeiException extends RuntimeException implements Serializable {

    private Integer status  = 500;

    private String message;


    public NeiException(){
        this("系统异常");
    }

    public NeiException(String message){
        super(message);
        this.message = message;
    }

    public NeiException(String message , int status ){
        super(message);
        this.message = message ;
        this.status = status;
    }

}
