package com.example.newapplication.new_utill;

//约定返回数据的格式和内容

/**
 * data 存放 数据库查询出来的数据
 *
 * @param <T>
 */

public class Result<T> {

    private String code;

    private String message;

    private T data;// 泛型


    public String getCode() {

        return code;

    }


    public void setCode(String code) {

        this.code = code;

    }


    public String getMessage() {

        return message;

    }


    public void setMessage(String message) {

        this.message = message;

    }


    public T getData() {

        return data;

    }


    public void setData(T data) {

        this.data = data;

    }


}
