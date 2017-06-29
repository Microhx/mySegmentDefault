package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.NewCollectionDataEntity;
import com.micro.mysegmentdefault.middle.UserAddNewCollectContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 23:08 <p>
 * interface :
 */

public class UserAddNewCollectModel implements UserAddNewCollectContract.AbsNewCollectModel {

    @Override
    public Observable<NewCollectionDataEntity> addNewCollect(String title, String desc, boolean isPrivate, String token) {
        return Api.
                getApiService(0).
                addUserNewCollectionDataEntity(title,desc,isPrivate?"1":"0",token).
                compose(RxSchedulers.<NewCollectionDataEntity>io_main());
    }
}
