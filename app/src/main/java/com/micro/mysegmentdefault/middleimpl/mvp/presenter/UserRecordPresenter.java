package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.UserRecordEntity;
import com.micro.mysegmentdefault.middle.UserRecordContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 11:29 <p>
 * interface :
 */

public class UserRecordPresenter extends UserRecordContract.Presenter {

    @Override
    public void loadingUserRecordInfo(String username) {
        mModel.loadingUserRecordInfo(username).subscribe(new Action1<UserRecordEntity>() {
            @Override
            public void call(UserRecordEntity userRecordEntity) {
                if(null != userRecordEntity && userRecordEntity.status == 0) {
                    mView.loadUserRecordInfo(userRecordEntity.data);
                }else {
                    mView.loadUserRecordInfoError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("UserRecordPresenter loading error:"+throwable);
                mView.loadUserRecordInfoError();
            }
        })    ;
    }
}
