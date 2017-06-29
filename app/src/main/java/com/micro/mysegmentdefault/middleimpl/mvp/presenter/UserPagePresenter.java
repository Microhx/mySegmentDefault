package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.UserPageEntity;
import com.micro.mysegmentdefault.middle.UserPagerContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 10:14 <p>
 * interface :
 */

public class UserPagePresenter extends UserPagerContract.AbsPresenter {

    @Override
    public void loadUserPagersInfo(String username) {
        mModel.loadUserPagerInfo(username).subscribe(new Action1<UserPageEntity>() {
            @Override
            public void call(UserPageEntity entity) {
                if (null != entity) {
                    mView.loadUserPagerInfo(entity.data);
                } else {
                    mView.loadUserPagerError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userPagePresenter loading error:" + throwable);
                mView.loadUserPagerError();
            }
        });
    }
}
