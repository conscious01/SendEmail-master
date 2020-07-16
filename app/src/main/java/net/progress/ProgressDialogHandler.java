package net.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 对话框Handler
 * Created by wison on 2017/3/8.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog dialog;

    private Context context;
    private boolean cancelable = false;
    private String msg;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(WeakReference<Context> weakReference, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.context = weakReference.get();
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    public ProgressDialogHandler(WeakReference<Context> weakReference, ProgressCancelListener mProgressCancelListener, String msg) {
        super();
        this.context = weakReference.get();
        this.mProgressCancelListener = mProgressCancelListener;
        this.msg = msg;
    }

    public ProgressDialogHandler(WeakReference<Context> weakReference, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable, String msg) {
        super();
        this.context = weakReference.get();
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        this.msg = msg;
    }

    private void initProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(cancelable);
            dialog.setMessage(msg);
            if (cancelable) {
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }
            if (!dialog.isShowing()) {
                if (context instanceof Activity && !((Activity) context).isFinishing()) {
                    dialog.show();
                }
            }
        }
    }

    private void dismissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
