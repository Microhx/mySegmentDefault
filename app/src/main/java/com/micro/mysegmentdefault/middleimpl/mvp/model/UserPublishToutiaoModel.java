package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.UserPublishToutiaoContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 19:33 <p>
 * interface :
 */

public class UserPublishToutiaoModel implements UserPublishToutiaoContract.AbsUserPublishToutiaoModel {

    @Override
    public Observable<OnlyData> updateToutiao(String url, String title, String type, String description, String token) {
        return Api.getApiService(0).updateToutiao(url,title,type,description,token).compose(RxSchedulers.<OnlyData>io_main());
    }
}
