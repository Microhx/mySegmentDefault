package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.UserPageEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserPagerContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 10:16 <p>
 * interface :
 */

public class UserPageModel implements UserPagerContract.AbsUserPagerModel {

    @Override
    public Observable<UserPageEntity> loadUserPagerInfo(String username) {
        return Api.
                getApiService(0).
                getUserPageDataEntity(username,"homepage", UserLogic.getUserToken()).
                compose(RxSchedulers.<UserPageEntity>io_main());
    }
}
