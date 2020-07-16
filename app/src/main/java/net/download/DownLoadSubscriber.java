package net.download;

import java.io.InputStream;

import rx.Subscriber;

/**
 *  不在UI线程
 * Created by wison on 2017/3/10.
 */
public class DownLoadSubscriber extends Subscriber<InputStream> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(InputStream inputStream) {

    }

}
