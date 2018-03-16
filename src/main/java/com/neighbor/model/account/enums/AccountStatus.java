package com.neighbor.model.account.enums;

public enum AccountStatus {

    ACCOUNT_NORMAL(0,"正常"),//帐号正常
    ACCOUNT_CLOSURE_TIME(1,"临时封紧"),//帐号冻结
    ACCOUNT_CLOSURE_FOREVER(2,"永久封禁");//永久封禁

    private Integer value;

    private String message;

    AccountStatus(Integer value ,String message){
        this.message = message;
        this.value =  value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
