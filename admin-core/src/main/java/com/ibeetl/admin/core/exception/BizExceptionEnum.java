package com.ibeetl.admin.core.exception;

/**
 * @author WuFengSheng on 2019/1/23.
 */
public enum BizExceptionEnum implements ServiceExceptionEnum{
    POS_LOW_STOCKS_ERROR(100, "POS机库存不足")
    ;

    private Integer code;

    private String message;

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
