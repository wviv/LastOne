package com.wa.last.mvc.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * 统一返回
 *
 * @author
 * @date
 */
@Data
@Builder
public class ResultBean<T> {

    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS = "SUCCESS";
    /**
     * 返回状态码
     */
    private Integer status;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回结果
     */
    private T result;

    public static <T> ResultBean<T> success(T t) {
        return ResultBean.<T>builder().status(SUCCESS_CODE).result(t).message(SUCCESS).build();
    }

    public static <T> ResultBean<T> error(Integer status, String message) {
        return ResultBean.<T>builder().status(status).message(message).build();
    }

    public Boolean isSuccess() {
        return Objects.equals(this.getStatus(), SUCCESS_CODE);
    }
}
