package com.ibeetl.admin.core.exception;

/**
 * @author WuFengSheng on 2019/1/23.
 */
public class GlobalException extends RuntimeException{

    private Integer code;

    private String message;

    public GlobalException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
