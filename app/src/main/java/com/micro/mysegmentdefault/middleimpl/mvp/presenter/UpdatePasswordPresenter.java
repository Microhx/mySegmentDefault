package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.UpdatePasswordEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UpdatePasswordContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 18:51 <p>
 * interface :
 */

public class UpdatePasswordPresenter extends UpdatePasswordContract.AbsUpdatePasswordPresenter {


    @Override
    public void updatePassword(String oldPassword, String newPassword, String newPasswordAgain) {
        mModel.updatePassword(oldPassword,newPassword,newPasswordAgain, UserLogic.getUserToken()).subscribe(new Action1<UpdatePasswordEntity>() {
            @Override
            public void call(UpdatePasswordEntity entity) {
                mView.onUpdatePasswordResult(entity);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("update password : " + throwable);
                mView.onUpdatePasswordResult(null);
            }
        });
    }
}
