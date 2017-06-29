package com.micro.mysegmentdefault.middle.view;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 18:27 <p>
 * interface :
 */

public interface AbsUserAddCollectView<D> extends BaseRefreshView<D>{

    //获取当前Context
    Context getContext();

    void addUserCollectionSuccess(String number);

    void addUserCollectionError();




}
