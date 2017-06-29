package com.micro.mysegmentdefault.network;

import android.content.Context;

import com.micro.mysegmentdefault.view.widget.CustomDialog;

import rx.Subscriber;

/**
 * author : micro_hx <p>
 * desc : 基于Rx的对话框<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 19:29 <p>
 * interface :
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mCtx ;

    private String msg ;

    //是否显示Dialog
    private boolean mShowDialog = true ;

    private CustomDialog mCustomDialog ;

    public RxSubscriber(Context mCtx , String msg) {
        this.mCtx = mCtx ;
        this.msg = msg;
    }

    @Override
    public void onStart() {
        if(mShowDialog) {
            if(null == mCustomDialog) {
                mCustomDialog = new CustomDialog(mCtx);
                mCustomDialog.show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        closeDialog();
        _onError(e);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onCompleted() {
        closeDialog();
    }

    private void closeDialog() {
        if(null != mCustomDialog) {
            mCustomDialog.cancel();
        }
    }

    public abstract void _onNext(T t) ;

    public abstract void _onError(Throwable t);


}
