package com.neighbor.exception;

/**
 * 异常返回类
 * @author wgf
 */
public class NeiException extends RuntimeException {

    private Integer code = 500;

    private String message;


    public NeiException(){
        this("系统异常");
    }

    public NeiException(String message){
        super(message);
    }

    public NeiException(String message , int code ){
        this.message = message ;
        this.code = code;
    }

}
