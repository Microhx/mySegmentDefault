package com.micro.mysegmentdefault.base.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 17:10 <p>
 * interface :
 */

public abstract class BaseRefreshPresenter<V extends BaseRefreshView,M extends BaseRefreshModel> extends BasePresenter<V,M> {

    public abstract void getCommonListDatas(int type ,String channel ,int startPages) ;
}
