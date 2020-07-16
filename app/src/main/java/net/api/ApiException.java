package net.api;

/**
 * 自定义ApiException
 * Created by wison on 2017/3/8.
 */
public class ApiException extends RuntimeException {

    private int resultCode;

    private int mErrorCode;

    //系统返回错误信息描述
    private String msg;

    public ApiException(String msg) {
        this.msg = msg;
    }

    public ApiException(int mErrorCode, String msg) {
        this.mErrorCode = mErrorCode;
        this.msg = msg;
    }

    public ApiException(int resultCode, int mErrorCode, String msg) {
        this.resultCode = resultCode;
        this.mErrorCode = mErrorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getMsg() {
        return msg;
    }

    public int getResultCode() {
        return resultCode;
    }
}
