package net.download;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 下载拦截器，监听下载进度
 * Created by wison on 2017/3/7
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener listener;
    private Handler handler;

    public DownloadProgressInterceptor(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}
