package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserPrivateEventDetailDataEntity;
import com.micro.mysegmentdefault.middle.model.AbsUserPrivateEventDetailModel;
import com.micro.mysegmentdefault.middle.view.AbsUserPrivateEventDetailView;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : 私信具体内容 presenter<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 21:59 <p>
 * interface :
 */

public class UserPrivateEventDetailPresenter extends BaseRefreshPresenter<AbsUserPrivateEventDetailView<UserPrivateEventDetailDataEntity.DataItem>, AbsUserPrivateEventDetailModel<UserPrivateEventDetailDataEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<UserPrivateEventDetailDataEntity>() {
            @Override
            public void call(UserPrivateEventDetailDataEntity entity) {
                if (null != entity && entity.status == 0) {
                    mView.getCommonListDatas(startPages, entity.data, null);
                } else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userPrivateEventDetail error: " + throwable);
                mView.getRequestError(startPages);
            }
        });
    }

    public void deletePrivateEvent(String mEventId) {
        mModel.onDeleteUserPrivateEvent(mEventId).
                subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.onDeletePrivateEventResult(true);
                }else{
                    mView.onDeletePrivateEventResult(false);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("deletePrivateEvent error: " + t);
                mView.onDeletePrivateEventResult(false);
            }
        });

    }
}
