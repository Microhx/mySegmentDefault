package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.middle.TopUserContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 23:01 <p>
 * interface :
 */

public class TopUserModel implements TopUserContract.TopUserModel {

    @Override
    public Observable<TopUserEntity> getTopUserEntity() {
        return Api.
                getApiService(0).
                getTopUserEntity().
                compose(RxSchedulers.<TopUserEntity>io_main());
    }
}
