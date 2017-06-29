package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.CommonResult;
import com.micro.mysegmentdefault.entity.TagDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.TagDetailContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;
import rx.functions.Func1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:03 <p>
 * interface :
 */

public class TagDetailModel implements TagDetailContract.DetailModel<TagDetailDataEntity> {

    @Override
    public Observable<TagDetailDataEntity> getTagDetailInfo(String tagName) {
        return Api.
                getApiService(0).
                getTagDetailDataEntity(tagName).
                compose(RxSchedulers.<TagDetailDataEntity>io_main());
    }

    @Override
    public Observable<Boolean> followTagStatus(String tagName) {
        return Api.
                getApiService(0).
                getFollowTagResult(tagName, UserLogic.getUserToken()).
                map(new Func1<CommonResult, Boolean>() {
            @Override
            public Boolean call(CommonResult result) {
                return null != result && result.status == 0;
            }
        }).compose(RxSchedulers.<Boolean>io_main());
    }

    @Override
    public Observable<Boolean> cancelFollowTagStatus(String tagName) {
        return Api.
                getApiService(0).
                getCancelFollowTagResult(tagName, UserLogic.getUserToken()).
                map(new Func1<CommonResult, Boolean>() {
                    @Override
                    public Boolean call(CommonResult result) {
                        return null != result && result.status == 0;
                    }
                }).compose(RxSchedulers.<Boolean>io_main());
    }
}
