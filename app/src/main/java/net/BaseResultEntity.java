package net;


import androidx.annotation.Keep;

/**
 * 统一格式返回类
 * Created by wison on 2017/3/7.
 */
@Keep
public class BaseResultEntity<T> {

    private int code = 0;
    private String msg;
    private T data;
    private int errcode;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
