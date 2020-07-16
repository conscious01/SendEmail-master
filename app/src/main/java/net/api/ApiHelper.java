package net.api;


import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import net.BaseResultEntity;
import net.HttpResultFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;

/**
 * 封装基本的网络操作
 * get post upload download
 * Created by wison on 2017/3/8.
 */
public class ApiHelper {

    public static Subscription doGet(String url, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doGet(url)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doGetWithParams(String url, Map<String, String> map, Subscriber<Object> subscriber) {

        if (url != null) {
        }
        if (map != null) {
        }
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doGetWithParams(url, map)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doGetWithHeader(String url, Map<String, String> map, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doGetWithHeader(url, map)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doGet(String url, Map<String, String> headers, Map<String, String> map, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doGet(url, headers, map)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doPost(String url, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doPost(url)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doPost(String url, RequestBody requestBody, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doPost(url, requestBody)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doPut(String url, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doPut(url)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doPut(String url, RequestBody requestBody, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doPut(url, requestBody)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doDelete(String url, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doDelete(url)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }

    public static Subscription doDelete(String url, RequestBody requestBody, Subscriber<Object> subscriber) {
        return ApiManager.getInstance()
                .create(ApiService.class)
                .doDelete(url, requestBody)
                .compose(ApiHelper.<BaseResultEntity<Object>>applySchedulers())
                .compose(ApiHelper.transformer())
                .subscribe(subscriber);
    }



    public static RequestBody CreateBody(String json) {
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    public static RequestBody CreateBody(Object object) {
        return RequestBody.create(MediaType.parse("application/json"), new GsonBuilder().disableHtmlEscaping().create().toJson(object));
    }

    public static <T> List<T> fromJsonList(JsonElement json, Class<T> cls) {
        List<T> mList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = json.getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    public static <T> T fromJsonBean(JsonElement json, Class<T> cls) {
        return new Gson().fromJson(json, cls);
    }

    //封装转换线程操作
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //对基本返回进行统一识别
    private static <T> Observable.Transformer<BaseResultEntity<T>, T> transformer() {
        return new Observable.Transformer<BaseResultEntity<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResultEntity<T>> entityObservable) {
                return entityObservable.map(new HttpResultFunc<T>());
            }
        };
    }



    public static <T> T parseJSON(String jsonStr, Class<T> t) {
        try {
            Gson gson = new Gson();
            T bean = gson.fromJson(jsonStr, t);
            return bean;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("TAG",e.getMessage());
            return null;
        }

    }


}

