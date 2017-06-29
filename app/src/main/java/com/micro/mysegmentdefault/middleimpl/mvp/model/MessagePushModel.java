package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.PushMessageDataEntity;
import com.micro.mysegmentdefault.middle.MessagePushContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 14:32 <p>
 * interface :
 */

public class MessagePushModel implements MessagePushContract.AbsMessagePushModel {

    @Override
    public Observable<PushMessageDataEntity> getMessagePushSetting(String token) {
        return Api.
                getApiService(0).
                userPushMessageDataEntity(token).
                compose(RxSchedulers.<PushMessageDataEntity>io_main());
    }


    @Override
    public Observable<PushMessageDataEntity> messagePushSetting(Map<String, String> params, String token) {
        return Api.
               getApiService(0).
               messagePushSetting(params,token).
               compose(RxSchedulers.<PushMessageDataEntity>io_main());
    }
}
