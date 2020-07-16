package net.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import net.HttpResultSubscriber;

/**
 * Api通用Subscriber
 * Created by wison on 2017/3/23.
 */
public abstract class ApiResultSubscriber extends HttpResultSubscriber<Object> {


    private static final String TAG = "ApiResultSubscriber";

    private boolean isNeedAllF = true;  // 有列表时是否需要整个data对象

    public ApiResultSubscriber() {
    }

    public ApiResultSubscriber(boolean isNeedAllF) {
        this.isNeedAllF = isNeedAllF;
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(Object object) {
        Log.i(TAG, "onNext--->"+object.toString());
        JsonElement element = new Gson().toJsonTree(object);
        onResponse(element);
//        if (element.isJsonObject()) {
//            JsonObject jsonObject = element.getAsJsonObject();
//            if (jsonObject.has("list")) {
//                if (!isNeedAllF) {
//                    JsonArray jsonArray = jsonObject.getAsJsonArray("list");
//                    onResponse(jsonArray);
//                } else {
//                    isNeedAllF = false;
//                    onResponse(element);
//                }
//            } else {
//                onResponse(element);
//            }
//        } else {
//            onResponse(element);
//        }





    }

    public abstract void onResponse(JsonElement json);

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);

    }
}
