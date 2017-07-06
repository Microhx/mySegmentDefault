package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.CreateSpecialColumnContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 17:04 <p>
 * interface :
 */

public class CreateSpecialColumnModel implements CreateSpecialColumnContract.AbsCreateSpecialColumnModel {

    @Override
    public Observable<OnlyData> commitUserSpecialColumn(String name, String slug, String description, String token) {
        return Api.getApiService(0).commitUserSpecialColumn(name,slug,description,token).compose(RxSchedulers.<OnlyData>io_main());
    }
}
