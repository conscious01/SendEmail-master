package net;


import androidx.annotation.Keep;

import rx.functions.Func1;

/**
 * 通用处理 Function
 * 当检测code不为200时将抛出异常到Subscriber的onError中做统一处理
 * {@link HttpResultSubscriber}
 * Created by wison on 2017/3/8.
 */
@Keep
public class HttpResultFunc<T> implements Func1<BaseResultEntity<T>, T> {

    @Override
    public T call(BaseResultEntity<T> entity) {

        return (T) entity;
    }

}
