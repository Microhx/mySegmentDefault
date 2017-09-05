package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.NewCollectionDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserAddNewCollectContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 22:51 <p>
 * interface :
 */

public class UserAddNewCollectPresenter extends UserAddNewCollectContract.AbsNewCollectPresenter {

    @Override
    public void addNewCollect(String mCollectTitle, String mCollectDes, boolean isPrivate) {
        mModel.addNewCollect(mCollectTitle,mCollectDes,isPrivate, UserLogic.getUserToken()).
                subscribe(new RxSubscriber<NewCollectionDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(NewCollectionDataEntity entity) {
                if(null != entity && entity.status == 0 ) {
                    mView.addNewCollectSuccess(entity.data.id,entity.data.name,entity.data.isPrivate);
                }else{
                    LogUtils.d("newCollect error : object is null");
                    mView.addNewCollectError();
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("addNewCollect error:" + t);
                    mView.addNewCollectError();
            }
        }) ;
    }

    @Override
    public void updateCollect(String collectId, String collectTitle, String collectDesc, boolean isPrivate) {
        mModel.updateNewCollect(collectId,collectTitle,collectDesc,isPrivate,UserLogic.getUserToken()).
                subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity dataEntity) {
                mView.updateCollectResult(dataEntity);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("---onError----" + t);
                mView.updateCollectResult(null);
            }
        }) ;
    }
}
