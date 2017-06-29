package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserPrivateEventDataEntity;
import com.micro.mysegmentdefault.entity.UserPrivateEventDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.model.AbsUserPrivateEventDetailModel;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.CommonUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 22:09 <p>
 * interface :
 */

public class UserPrivateEventDetailModel implements AbsUserPrivateEventDetailModel<UserPrivateEventDetailDataEntity> {

    @Override
    public Observable<UserPrivateEventDetailDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getUserPrivateEventDetailDataEntity(channel, CommonUtils.getCurrentSecondTime(), UserLogic.getUserToken()).
                compose(RxSchedulers.<UserPrivateEventDetailDataEntity>io_main());
    }


    @Override
    public Observable<BaseDataEntity> onDeleteUserPrivateEvent(String eventId) {
        return Api.
                getApiService(0).
                userDeletePrivateEventData(eventId,UserLogic.getUserToken()).
                compose(RxSchedulers.<BaseDataEntity>io_main());
    }
}
