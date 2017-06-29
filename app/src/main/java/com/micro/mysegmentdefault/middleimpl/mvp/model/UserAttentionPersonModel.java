package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.UserAttentionPersonDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 21:59 <p>
 * interface :
 */

public class UserAttentionPersonModel implements BaseRefreshModel<UserAttentionPersonDataEntity> {

    @Override
    public Observable<UserAttentionPersonDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                userAttentionDataEntity(channel, UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<UserAttentionPersonDataEntity>io_main());
    }
}
