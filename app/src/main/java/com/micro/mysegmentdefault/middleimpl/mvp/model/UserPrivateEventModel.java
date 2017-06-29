package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.UserPrivateEventDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 18:12 <p>
 * interface :
 */

public class UserPrivateEventModel implements BaseRefreshModel<UserPrivateEventDataEntity> {

    @Override
    public Observable<UserPrivateEventDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return  Api.
                getApiService(0).
                getUserPrivateEventDataEntity(channel, UserLogic.getUserToken(),String.valueOf(startPages)).
                compose(RxSchedulers.<UserPrivateEventDataEntity>io_main());
    }
}
