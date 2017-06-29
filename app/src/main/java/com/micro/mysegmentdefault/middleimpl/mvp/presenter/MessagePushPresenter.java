package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.entity.PushMessageDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.MessagePushContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.Map;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 14:23 <p>
 * interface :
 */

public class MessagePushPresenter extends MessagePushContract.AbsMessagePushPresenter {

    @Override
    public void getMessagePushSetting() {
        mModel.getMessagePushSetting(UserLogic.getUserToken()).subscribe(new Action1<PushMessageDataEntity>() {
            @Override
            public void call(PushMessageDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.showMessagePushItem(entity);
                }else {
                    mView.onRequestDataError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("message push error : " + throwable);
                mView.onRequestDataError();
            }
        }) ;
    }

    @Override
    public void messagePushSetting(Map<String, String> params) {
        mModel.messagePushSetting(params,UserLogic.getUserToken()).subscribe(new Action1<PushMessageDataEntity>() {
            @Override
            public void call(PushMessageDataEntity entity) {
                mView.onMessagePushSettingResult(null != entity && entity.status == 0);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("message push setting error : " + throwable);
                mView.onMessagePushSettingResult(false);
            }
        });
    }
}
