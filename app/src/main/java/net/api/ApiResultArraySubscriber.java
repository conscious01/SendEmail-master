package net.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.HttpResultSubscriber;

/**
 * Api通用Subscriber
 * Created by wison on 2017/3/23.
 */
@Deprecated
public abstract class ApiResultArraySubscriber extends HttpResultSubscriber<Object> {

    @Override
    public void onNext(Object object) {
        onResponse(new Gson().toJsonTree(object).getAsJsonArray());
    }

    public abstract void onResponse(JsonArray jsonArray);

}
