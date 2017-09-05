package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserCollectView;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectionDetailModel;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:22 <p>
 * interface :
 */

public class UserCollectionDetailPresenter extends BaseRefreshPresenter<AbsUserCollectView, UserCollectionDetailModel> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {

        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<UserCollectionDetailDataEntity>() {
            @Override
            public void call(UserCollectionDetailDataEntity entity) {
                if (null != entity && entity.status == 0) {
                    mView.updateUserCollectionInfo(entity.data.parent);

                    mView.getCommonListDatas(startPages, entity.data.rows, entity.data.page);
                } else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userCollectionDetail error : " + throwable);

                mView.getRequestError(startPages);
            }
        });
    }

    public void deleteBookmark(String mId) {
        mModel.deleteUserBookMark(mId).
               subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity dataEntity) {
                mView.deleteUserBookmark(dataEntity);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("delete book mark error : " + t);
                mView.deleteUserBookmark(null);
            }
        }) ;
    }
}
