package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.UserTimeLineDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/28 - 14:33 <p>
 * interface :
 */

public class UserTimeLineModel implements BaseRefreshModel<UserTimeLineDataEntity> {

    @Override
    public Observable<UserTimeLineDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getUserTimeLineDataEntity(channel, UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<UserTimeLineDataEntity>io_main());
    }
}
