package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:39 <p>
 * interface :
 */

public class UserCollectionDetailModel implements BaseRefreshModel<UserCollectionDetailDataEntity> {

    @Override
    public Observable<UserCollectionDetailDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                userCollectionDetailDataEntity(channel, UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<UserCollectionDetailDataEntity>io_main());
    }
}
