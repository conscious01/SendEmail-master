package net.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Api 返回指定 model Subscriber
 * Created by wison on 2017/12/14.
 */
public abstract class ApiListResSubscriber<T> extends ApiResultSubscriber {

    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(JsonElement json) {
        List<T> mList = new ArrayList<>();
        Gson gson = new Gson();
        for(JsonElement elem : json.getAsJsonArray()) {
            mList.add((T) gson.fromJson(elem, new TypeToken<T>() {}.getType()));
        }
        onResponse(mList);
    }

    public abstract void onResponse(List<T> t);

}
