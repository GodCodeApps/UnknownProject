package com.lib_common.entity;

/**
 * 统一返回数据
 */
public class BaseBean<T> {
    //状态码
    private int code;
    //信息
    private String msg;
    //数据部分
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        code = code;
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
    public boolean isOk() {
        return code == 0;
    }
    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
