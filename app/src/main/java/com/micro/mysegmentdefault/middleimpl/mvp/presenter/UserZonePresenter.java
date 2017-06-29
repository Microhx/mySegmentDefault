package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.UserDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserZoneContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 9:30 <p>
 * interface :
 */

public class UserZonePresenter extends UserZoneContract.Presenter {

    @Override
    public void loadUserZoneBaseInfo(String username) {
        mModel.getUserZoneData(username).subscribe(new Action1<UserDataEntity>() {
            @Override
            public void call(UserDataEntity entity) {
                if (null != entity) {
                    mView.loadUserZoneData(entity);
                } else {
                    mView.loadUserZoneError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userZonePresenter loading error:" + throwable);
                mView.loadUserZoneError();
            }
        });
    }
}
