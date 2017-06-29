package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.SocialAccountBindDataEntity;
import com.micro.mysegmentdefault.middle.SocialAccountShowContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 22:22 <p>
 * interface :
 */

public class SocialAccountModel implements SocialAccountShowContract.AbsSocialAccountModel {

    @Override
    public Observable<SocialAccountBindDataEntity> loadSocialAccountInfo(String token) {
        return Api.
                getApiService(0).
                getSocialAccountBindDataEntity(token).
                compose(RxSchedulers.<SocialAccountBindDataEntity>io_main());
    }

    @Override
    public Observable<OnlyData> updateSocialAccountInfo(String type, boolean isOpen , String token) {
        return Api.
                getApiService(0).
                updateSocialAccountInfo(type, isOpen?"show":"hide", token).
                compose(RxSchedulers.<OnlyData>io_main());
    }
}
