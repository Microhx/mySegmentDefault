package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.PushMessageDataEntity;

import java.util.Map;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 14:25 <p>
 * interface :
 */

public interface MessagePushContract {

    interface AbsMessagePushModel extends BaseModel {
        rx.Observable<PushMessageDataEntity> getMessagePushSetting(String token);
        rx.Observable<PushMessageDataEntity> messagePushSetting(Map<String,String> params,String token);
    }

    interface AbsMessagePushView extends BaseView {
        void showMessagePushItem(PushMessageDataEntity data);
        void onRequestDataError();
        void onMessagePushSettingResult(boolean result);
    }

    abstract class AbsMessagePushPresenter extends BasePresenter<AbsMessagePushView,AbsMessagePushModel> {
        public abstract void getMessagePushSetting();
        public abstract void messagePushSetting(Map<String,String> params);
    }


}
