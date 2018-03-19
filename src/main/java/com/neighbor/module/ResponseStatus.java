package com.neighbor.module;

public enum ResponseStatus {

    RESPONSE_STATUS_200(200,"正常"),
    RESPONSE_STATUS_401(401,"未授权"),
    RESPONSE_STATUS_500(500,"系统错误"),
    RESPONSE_STATUS_417(417,"不满足条件")
    ;

    private Integer value;

    private String message;

    ResponseStatus(Integer value , String message){
        this.message = message;
        this.value = value;
    }
}
