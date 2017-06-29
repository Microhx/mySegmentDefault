package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserCollectView;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:22 <p>
 * interface :
 */

public class UserCollectionDetailPresenter extends BaseRefreshPresenter<AbsUserCollectView, BaseRefreshModel> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {

        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<UserCollectionDetailDataEntity>() {
            @Override
            public void call(UserCollectionDetailDataEntity entity) {
                if (null != entity && entity.status == 0) {
                    String title = entity.data.parent.title;
                    String count = entity.data.parent.num;
                    String userName = entity.data.parent.user.name;
                    String userPhoto = entity.data.parent.user.avatarUrl;
                    mView.updateUserOtherCollectInfo(userName, userPhoto, title, count);

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
}
