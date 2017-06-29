package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.LocationDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 17:47 <p>
 * interface :
 */

public class LocationChooseModel implements BaseRefreshModel<LocationDataEntity> {

    @Override
    public Observable<LocationDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        if(type == 0) {
            return Api.getApiService(0).loadAllProvincesDataEntity(UserLogic.getUserToken()).compose(RxSchedulers.<LocationDataEntity>io_main());
        }

        return Api.getApiService(0).loadCityDataEntity(channel,UserLogic.getUserToken()).compose(RxSchedulers.<LocationDataEntity>io_main());
    }
}
