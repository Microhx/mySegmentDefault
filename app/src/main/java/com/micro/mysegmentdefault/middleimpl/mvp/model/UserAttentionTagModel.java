package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.UserAttentionPersonDataEntity;
import com.micro.mysegmentdefault.entity.UserAttentionTagDataEntity;
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

public class UserAttentionTagModel implements BaseRefreshModel<UserAttentionTagDataEntity> {

    @Override
    public Observable<UserAttentionTagDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                userAttentionTagDataEntity(UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<UserAttentionTagDataEntity>io_main());
    }
}
