package com.ifchange.regressiontest.dto;

/**
 * @ClassName: ExceptionDto
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 16:01 2019/1/14
 */
public class ExceptionDto extends RuntimeException {
    private String msg;
    private int code = 500;

    public ExceptionDto(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ExceptionDto(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ExceptionDto(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ExceptionDto(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
