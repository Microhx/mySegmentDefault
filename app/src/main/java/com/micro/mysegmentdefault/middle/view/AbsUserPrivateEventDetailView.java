package com.micro.mysegmentdefault.middle.view;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 23:16 <p>
 * interface :
 */

public interface AbsUserPrivateEventDetailView<D> extends BaseRefreshView<D> {

    Context getContext() ;


    void onDeletePrivateEventResult(boolean result);

}
