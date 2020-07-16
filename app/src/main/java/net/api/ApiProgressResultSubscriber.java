package net.api;

import android.content.Context;

import net.progress.ProgressSubscriber;


/**
 * Api通用Subscriber，带ProgressDialog
 * Created by wison on 2017/5/11.
 */
public abstract class ApiProgressResultSubscriber extends ProgressSubscriber<Object> {

    public ApiProgressResultSubscriber(Context context) {
        super(context);
    }

    public ApiProgressResultSubscriber(Context context, String msg) {
        super(context, msg);
    }

    public ApiProgressResultSubscriber(Context context, boolean cancelable, String msg) {
        super(context, cancelable, msg);
    }

//    @Override
//    public void onNext(Object object) {
//        onResponse(new Gson().toJsonTree(object));
//    }
//
//    public abstract void onResponse(JsonElement json);
}
