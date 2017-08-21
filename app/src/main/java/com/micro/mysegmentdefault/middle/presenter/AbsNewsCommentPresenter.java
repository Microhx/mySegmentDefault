package com.micro.mysegmentdefault.middle.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 15:02 <p>
 * interface :
 */

public abstract class AbsNewsCommentPresenter<V extends BaseRefreshView,M extends BaseRefreshModel> extends BaseRefreshPresenter<V,M> {

    public abstract void zanOperation(String type , boolean isCancel, String newsId) ;

}
