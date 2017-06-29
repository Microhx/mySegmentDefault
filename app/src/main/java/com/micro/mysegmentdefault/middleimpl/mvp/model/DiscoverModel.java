package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.middle.DiscoverContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 23:15 <p>
 * interface :
 */

public class DiscoverModel implements DiscoverContract.DiscoverModel {

    @Override
    public Observable<DiscoverDataEntity> getDiscoverDataEntity() {
        return Api.getApiService(0).
                getActivityDiscoverDataEntity().
                compose(RxSchedulers.<DiscoverDataEntity>io_main());
    }
}
