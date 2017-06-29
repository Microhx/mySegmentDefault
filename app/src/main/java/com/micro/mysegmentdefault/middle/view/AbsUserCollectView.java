package com.micro.mysegmentdefault.middle.view;

import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:33 <p>
 * interface :
 */

public interface AbsUserCollectView<D> extends BaseRefreshView<D> {

    void updateUserOtherCollectInfo(String userName , String userPhoto , String collectTitle , String collectCount);
}
