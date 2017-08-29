package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.UserTimeLineDataEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/28 - 14:28 <p>
 * interface :
 */

public class UserTimeLinePresenter extends BaseRefreshPresenter<BaseRefreshView<UserTimeLineDataEntity.DataItem>,
        BaseRefreshModel<UserTimeLineDataEntity>> {
    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<UserTimeLineDataEntity>() {
            @Override
            public void call(UserTimeLineDataEntity userTimeLineDataEntity) {
                mView.getCommonListDatas(startPages,userTimeLineDataEntity.data.rows,userTimeLineDataEntity.data.page);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userTimeLine request error : " + throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
