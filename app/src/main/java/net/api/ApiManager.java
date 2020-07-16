package net.api;

import com.example.sendemail.AppConstants;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.JsonUtil;

/**
 * ApiManager
 * 统一处理网络初始化
 * Created by wison on 2017/3/7
 */
public class ApiManager {

    //连接超时时间
    private static final long CONNECT_TIMEOUT = 5;

    //写入读取超时时间
    private static final long WRITE_READ_TIMEOUT = 20;

    private volatile static ApiManager manager;

    private OkHttpClient.Builder client;
    private Retrofit retrofit;

    public static ApiManager getInstance() {
        if (manager == null) {
            synchronized (ApiManager.class) {
                if (manager == null) {
                    manager = new ApiManager();
                }
            }
        }
        return manager;
    }

    private ApiManager() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initOkHttpClient() {

        //在debug模式下添加日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (AppConstants.isLog && JsonUtil.isJson(message)) {
                } else if (AppConstants.isLog) {
                }
            }
        });
        httpLoggingInterceptor.setLevel(AppConstants.isLog ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);





        client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(WRITE_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_READ_TIMEOUT, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrlMgr.getBaseURL())
                .client(client.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}