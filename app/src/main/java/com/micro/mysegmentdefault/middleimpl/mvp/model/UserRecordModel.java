package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.UserRecordEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserRecordContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 11:31 <p>
 * interface :
 */

public class UserRecordModel implements UserRecordContract.AbsUserRecordModel {

    @Override
    public Observable<UserRecordEntity> loadingUserRecordInfo(String username) {
        return Api.
                getApiService(0).
                getUserRecordDataEntity(username,"profile", UserLogic.getUserToken()).
                compose(RxSchedulers.<UserRecordEntity>io_main());
    }
}
