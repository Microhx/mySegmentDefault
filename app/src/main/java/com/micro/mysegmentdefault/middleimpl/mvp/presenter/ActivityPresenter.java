package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.ActivityDetailContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/27 - 17:31 <p>
 * interface :
 */

public class ActivityPresenter extends ActivityDetailContract.AbsActivityPresenter {
    @Override
    public void getActivityInfo(String activityId) {
        mModel.getActivityInfo(activityId, UserLogic.getUserToken()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mView.showActivityInfo(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("getActivityInfo : " + throwable);
                mView.showActivityInfo("");
            }
        }) ;
    }


}
