package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.UpdatePasswordEntity;
import com.micro.mysegmentdefault.middle.UpdatePasswordContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 18:52 <p>
 * interface :
 */

public class UpdatePasswordModel implements UpdatePasswordContract.AbsUpdatePasswordModel {

    @Override
    public Observable<UpdatePasswordEntity> updatePassword(String oldPassword, String newPassword, String newPasswordAgain, String token) {
        return Api.
                getApiService(0).
                updateUserPassword(oldPassword,newPassword,newPasswordAgain,token).
                compose(RxSchedulers.<UpdatePasswordEntity>io_main());
    }
}
