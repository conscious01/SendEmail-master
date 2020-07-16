package net.download;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 下载图片、文件
 * Created by wison on 2017/3/7
 */
public class DownLoadManager {

    //连接超时时间
    private static final long CONNECT_TIMEOUT = 15;

    //写入读取超时时间
    private static final long WRITE_READ_TIMEOUT = 30;

    private OkHttpClient.Builder client;
    private Retrofit retrofit;

    public DownLoadManager(String url, DownloadProgressListener listener) {
        initOkHttpClient(listener);
        initRetrofit(url);
    }

    private void initOkHttpClient(DownloadProgressListener listener) {

        DownloadProgressInterceptor downloadInterceptor = new DownloadProgressInterceptor(listener);

        client = new OkHttpClient.Builder()
                .addInterceptor(downloadInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_READ_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(WRITE_READ_TIMEOUT, TimeUnit.SECONDS);
    }

    private void initRetrofit(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}