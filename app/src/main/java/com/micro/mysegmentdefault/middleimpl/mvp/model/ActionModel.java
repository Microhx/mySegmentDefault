package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.ActionDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 21:35 <p>
 * interface :
 */

public class ActionModel implements BaseRefreshModel<ActionDataEntity> {

    @Override
    public Observable<ActionDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        LogUtils.d("channel ---->>> " + channel);

        return Api.
                getApiService(0).
                getActionDataEntity(UserLogic.getUserToken(), channel).
                compose(RxSchedulers.<ActionDataEntity>io_main());
    }
}
