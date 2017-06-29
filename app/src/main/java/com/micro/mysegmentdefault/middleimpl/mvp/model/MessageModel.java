package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.MessageDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : 用户消息  <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/7 - 15:14 <p>
 * interface :
 */

public class MessageModel implements BaseRefreshModel<MessageDataEntity> {

    @Override
    public Observable<MessageDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getUserMessageEventDataEntity(getTargetType(type), UserLogic.getUserToken(),String.valueOf(startPages)).
                compose(RxSchedulers.<MessageDataEntity>io_main());
    }

    private String getTargetType(int type) {
        if(type == 0) return "general";
        if(type == 1) return "ranked";
        return "followed";

    }
}
