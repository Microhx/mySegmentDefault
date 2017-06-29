package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.UserDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserZoneContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 9:32 <p>
 * interface :
 */

public class UserZoneModel implements UserZoneContract.UserZoneModel {

    @Override
    public Observable<UserDataEntity> getUserZoneData(String username) {
        return Api.
                getApiService(0).
                getUserZoneDataEntity(username,"base", UserLogic.getUserToken()).
                compose(RxSchedulers.<UserDataEntity>io_main());
    }
}
