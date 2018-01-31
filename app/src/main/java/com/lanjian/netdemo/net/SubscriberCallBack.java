package com.lanjian.netdemo.net;

import android.app.Activity;
import android.util.Log;

import rx.Subscriber;

/**
 * Created by dali on 2017/8/4.
 */

public abstract class SubscriberCallBack<T> extends Subscriber<T> {

    public static final String TOKEN_MISSING = "401";// token 丢失
    public static final String TOKEN_ERROR = "402"; // token 错误
    public static final String TOKEN_EXPIRE = "403"; // token 过期
    public static final String TOKEN_NOT_ALLOWED = "405"; // token 不允许

    private Activity mContext;

    private boolean isShowDialog = false;

    public SubscriberCallBack(Activity context) {
        mContext = context;
    }

    public SubscriberCallBack(Activity context, boolean isShowDialog) {
        this.mContext = context;
        this.isShowDialog = isShowDialog;
    }

    public abstract void onSuccess(T result);

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

        if (e != null) {
            if (e.getMessage().contains(TOKEN_MISSING) || e.getMessage().contains(TOKEN_EXPIRE)
                    || e.getMessage().contains(TOKEN_ERROR) || e.getMessage().contains(TOKEN_NOT_ALLOWED)) {
                if (mContext != null) {
                }
            }
            Log.i("tag","net error" + e.getMessage());
        } else {
            Log.i("tag","错误");
        }
    }

    @Override
    public void onNext(T result) {
        try {
                onSuccess(result);
        } catch (Exception e) {
            onError(e);
        }

    }
}
