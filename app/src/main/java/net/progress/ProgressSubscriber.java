package net.progress;

import android.content.Context;

import net.api.ApiResultSubscriber;

import java.lang.ref.WeakReference;

/**
 * 发送请求弹出一个ProgressDialog
 * 取消ProgressDialog的同时取消当前的请求
 * Created by wison on 2017/3/8.
 */
public abstract class ProgressSubscriber<T> extends ApiResultSubscriber implements ProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressSubscriber(Context context) {
        WeakReference<Context> mViewRef = new WeakReference<>(context);
        mProgressDialogHandler = new ProgressDialogHandler(mViewRef, this, false);
    }

    public ProgressSubscriber(Context context, String msg) {
        WeakReference<Context> mViewRef = new WeakReference<>(context);
        mProgressDialogHandler = new ProgressDialogHandler(mViewRef, this, msg);
    }

    public ProgressSubscriber(Context context, boolean cancelable, String msg) {
        WeakReference<Context> mViewRef = new WeakReference<>(context);
        mProgressDialogHandler = new ProgressDialogHandler(mViewRef, this, cancelable, msg);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

}
