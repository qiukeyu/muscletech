package com.example.demo.common;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode(Constants.SUCCESS);
        result.setMsg("successful");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode(Constants.SUCCESS);
        result.setMsg("successful");
        return result;
    }

    public static Result error(String code) {
        Result result = new Result();
        result.setCode(code);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
