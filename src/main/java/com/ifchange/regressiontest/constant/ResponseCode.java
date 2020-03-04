package com.ifchange.regressiontest.constant;

/**
 * @ClassName: ResponseCode
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:01 2019/4/2
 */
public enum ResponseCode {
    SUCCESS(1000, "请求成功"),
    PARAM_CORRECT(1001, "参数正确"),
    ERROR(2000, "请求失败"),
    PARAM_INCORRECT(2001, "参数错误"),
    LOGIN_FAILED(2002, "登录失败"),
    UNLOGIN(2003, "未登录"),
    FOUND_RESULT(2004, "未获取到查询结果");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
