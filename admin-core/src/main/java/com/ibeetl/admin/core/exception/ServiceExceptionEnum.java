package com.ibeetl.admin.core.exception;

/**
 * @author WuFengSheng on 2019/1/23.
 */
public interface ServiceExceptionEnum {
    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
