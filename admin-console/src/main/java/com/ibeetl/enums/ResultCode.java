package com.ibeetl.enums;

import com.ibeetl.admin.core.exception.ServiceExceptionEnum;

/**
 * ClassName: ResultCode
 *
 * @author guocp Description 执行结果code枚举
 * <p>0: 执行成功 ,
 * <p>-1: 执行失败 ,
 * <p>1000到1500之内为系统错误
 * <p>1500以上为业务错误
 */
public enum ResultCode implements ServiceExceptionEnum {
    /**
     * 0:执行成功
     */
    SUCCESS(0, "操作成功"),

    /**
     * -1:执行失败
     */
    FAIL(-1, "操作失败"),

    /**
     * 1000:业务异常
     */
    BUSINESS_ERROR(1000, "业务异常"),

    /**
     * 1001:非法请求
     */
    UNLAW_REQUEST(1001, "非法请求"),

    /**
     * 1002:未登录或token失效
     */
    NOT_LOGIN(1002, "未登录或token失效"),

    /**
     * 1003:图片上传失败
     */
    UPLOAD_FAIL(1003, "文件上传失败"),

    /**
     * 1004:用户调用次数超限
     */
    USER_CALL_LIMITED(1004, "用户调用次数超限"),

    /**
     * 1005:会话调用次数超限
     */
    SESSION_CALL_LIMITED(1005, "会话调用次数超限"),

    /**
     * 1006:应用调用次数超限
     */
    APP_CALL_LIMITED(1006, "应用调用次数超限"),

    /**
     * 1007:应用调用频率超限
     */
    APP_CALL_EXCEEDS_LIMITED_FREQUENCY(1007, "应用调用频率超限"),

    /**
     * 1008:服务不可用
     */
    SERVICE_CURRENTLY_UNAVAILABLE(1008, "服务不可用"),

    /**
     * 1009:远程服务出错
     */
    REMOTE_SERVICE_ERROR(1009, "服务调用出错"),

    /**
     * 1010:缺少方法名参数
     */
    MISSING_METHOD(1010, "缺少方法名参数"),

    /**
     * 1011:不存在的方法名
     */
    INVALID_METHOD(1011, "不存在的方法名"),
    /**
     * 1012:非法数据格式
     */
    INVALID_FORMAT(1012, "非法数据格式"),
    /**
     * 1013:缺少签名参数
     */
    MISSING_SIGNATURE(1013, "缺少签名参数"),
    /**
     * 1014:非法签名
     */
    INVALID_SIGNATURE(1014, "非法签名"),
    /**
     * 1015:缺少版本参数
     */
    MISSING_VERSION(1015, "缺少版本参数"),
    /**
     * 1016:非法的版本参数
     */
    INVALID_VERSION(1016, "非法的版本参数"),
    /**
     * 1017:不支持的版本号
     */
    UNSUPPORTED_VERSION(1017, "不支持的版本号"),
    /**
     * 1018:缺少必选参数
     */
    MISSING_REQUIRED_ARGUMENTS(1018, "缺少必选参数"),
    /**
     * 1019:非法的参数
     */
    INVALID_ARGUMENTS(1019, "非法的参数"),
    /**
     * 1020:请求被禁止
     */
    FORBIDDEN_REQUEST(1020, "请求被禁止"),
    /**
     * 1021:参数错误
     */
    PARAMETER_ERROR(1021, "参数错误"),

    /**
     * 1022:没有操作权限
     */
    NOT_AUTHORITY(1022, "没有操作权限"),

    /**
     * 1023:请求无数据
     */
    DATA_NOT_EXIST(1023, "请求无数据"),

    /**
     * 1024:请求无数据
     */
    LOGIN_ERROR(1024, "登入失败，请检查用户或密码"),

    /**
     * 业务编码
     * 2000~5000
     */
    DICTIONARY_CODE_REPEAT(2001, "数据字典code重复"),

    NO_DICTIONARY(2002, "数据字典不存在"),

    ADDRESS_PARENT_NOT_EXIST(2003, "父城市不存在"),

    ADDRESS_NOT_EXIST(2004, "城市不存在"),

    USER_ACCOUNT_VALIDE_AMOUNT_NOT_ENOUGH(3999, "账户可提现金额不足"),

    ORDER_INFO_NOT_EXIST(5027, "订单不存在"),

    VERIFY_CODE_ERROR(11001, "验证码错误"),
    VERIFY_CODE_IS_NEED(11002, "缺少验证码"),;

    private Integer code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 根据code值换取中文提示信息
     *
     * @param code 错误码
     */
    public static ResultCode enumValueOf(int code) {
        for (ResultCode codeEnum : ResultCode.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        return null;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
