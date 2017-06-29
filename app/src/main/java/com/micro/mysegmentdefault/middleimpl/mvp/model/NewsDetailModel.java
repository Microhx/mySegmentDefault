package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.NewsDetailContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/15 - 22:11 <p>
 * interface :
 */

public class NewsDetailModel implements NewsDetailContract.DetailModel {


    @Override
    public Observable<NewsDetailDataEntity> getNewsDataDetailDataEntity(String newsId) {
        return Api.
                getApiService(0).
                getNewsDetailData(newsId , UserLogic.getUserToken()).
                compose(RxSchedulers.<NewsDetailDataEntity>io_main());
    }

    @Override
    public Observable<BaseDataEntity> getZanOperationDataEntity(boolean isCancel, String newsId) {
        if(isCancel) {
            return Api.getApiService(0).userDisLikeNewsDataEntity(newsId,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else {
            return Api.getApiService(0).userLikeNewsDataEntity(newsId,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }
    }


    @Override
    public Observable<FollowDataEntity> followOrCancelUser(boolean isCancel, String userId) {
        if(isCancel){
            return Api.getApiService(0).userCancelFollowDataEntity(userId,UserLogic.getUserToken()).compose(RxSchedulers.<FollowDataEntity>io_main());
        }else {
            return Api.getApiService(0).userFollowDataEntity(userId,UserLogic.getUserToken()).compose(RxSchedulers.<FollowDataEntity>io_main());
        }
    }
}
