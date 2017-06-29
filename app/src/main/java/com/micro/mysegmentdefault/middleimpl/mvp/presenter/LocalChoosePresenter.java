package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.LocationDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : 用户居住地选择 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 17:42 <p>
 * interface :
 */

public class LocalChoosePresenter extends BaseRefreshPresenter<BaseRefreshView,BaseRefreshModel> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {

        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<LocationDataEntity>() {
            @Override
            public void call(LocationDataEntity entity) {
                if (null != entity && !CommonUtils.collectionIsNull(entity.data)) {
                    mView.getCommonListDatas(startPages, entity.data, null);
                } else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("LocationChoose error " + throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
