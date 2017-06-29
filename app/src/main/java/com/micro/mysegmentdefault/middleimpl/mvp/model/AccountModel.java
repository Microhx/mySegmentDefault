package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.ThirdPlatformDataEntity;
import com.micro.mysegmentdefault.middle.AccountSettingContract;
import com.micro.mysegmentdefault.network.Api;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 22:59 <p>
 * interface :
 */

public class AccountModel implements AccountSettingContract.AbsAccountModel {

    @Override
    public Observable<AccountDataEntity> getAccountDataEntity(String userUrl, String token) {
        //获取全部数据
        return Api.getApiService(0).getAccountDataEntity(userUrl,token,"all");
    }

    @Override
    public Observable<ThirdPlatformDataEntity> getThirdPlatformDataEntity(String token) {
        return Api.getApiService(0).getThirdPlatformDataEntity(token);
    }
}
