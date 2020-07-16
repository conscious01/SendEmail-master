package net;


import net.api.ApiException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 通用 Subscriber
 * Created by wison on 2017/3/7.
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {

        if (throwable instanceof ApiException) {
            ApiException apiException = (ApiException) throwable;


        } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
        } else if (throwable instanceof NullPointerException) {
        } else if (throwable instanceof HttpException) {


            throwable.printStackTrace();
        }

    }
}
