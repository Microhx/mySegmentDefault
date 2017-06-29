package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.model.AbsUserCollectModel;
import com.micro.mysegmentdefault.middle.presenter.AbsUserAddCollectPresenter;
import com.micro.mysegmentdefault.middle.view.AbsUserAddCollectView;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.Map;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 15:23 <p>
 * interface :
 */


public class UserAddCollectPresenter extends AbsUserAddCollectPresenter<AbsUserAddCollectView<UserCollectEntity.CollectItem>, AbsUserCollectModel<UserCollectEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<UserCollectEntity>() {
            @Override
            public void call(UserCollectEntity entity) {
                if (null != entity && null != entity.data) {
                    mView.getCommonListDatas(startPages, entity.data.rows, entity.data.page);
                } else {
                    mView.getRequestError(startPages);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("UserCollect request error:" + throwable);
                mView.getRequestError(startPages);
            }
        });
    }


    @Override
    public void addUserCollectDataEntity(String newsId,int tagType, Map<String, String> collectIds) {
        mModel.userAddCollectDataEntity(newsId, tagType, collectIds, UserLogic.getUserToken()).
               subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(), "") {
                    @Override
                    public void _onNext(BaseDataEntity entity) {
                        if (null != entity && entity.status == 0) {
                            mView.addUserCollectionSuccess(entity.data);
                        } else {
                            mView.addUserCollectionError();
                        }
                    }

                    @Override
                    public void _onError(Throwable t) {
                        LogUtils.d("add User collection error : " + t);
                        mView.addUserCollectionError();
                    }
                });
    }

}
