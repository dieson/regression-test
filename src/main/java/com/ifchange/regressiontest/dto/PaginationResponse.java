package com.ifchange.regressiontest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.model.Page;

import java.io.Serializable;

/**
 * @ClassName: PaginationResponse
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 9:53 2019/4/25
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;
    private Page pagination;


    private PaginationResponse(int code, T data, Page pagination) {
        this.code = code;
        this.data = data;
        this.pagination = pagination;
    }

    private PaginationResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> PaginationResponse<T> createBySuccess(T data, Page pagination) {
        return new PaginationResponse<>(ResponseCode.SUCCESS.getCode(), data, pagination);
    }


    public static <T> PaginationResponse<T> createByErrorMessage(String errorMessage) {
        return new PaginationResponse<>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Page getPagination() {
        return pagination;
    }
}
