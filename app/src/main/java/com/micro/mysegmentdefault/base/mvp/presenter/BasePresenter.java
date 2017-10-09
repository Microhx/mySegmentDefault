package com.micro.mysegmentdefault.base.mvp.presenter;

import android.content.Context;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/19 - 23:24 <p>
 * interface :
 */

public abstract class BasePresenter<T,E>  {

    public Context mContext ;
    public T mView ;
    public E mModel ;

    public void setVM(T t , E e) {
        this.mView = t ;
        this.mModel = e ;
        this.onStart();
    }

    public void onStart() {}


}
