package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserBlogDataEntity;
import com.micro.mysegmentdefault.middle.WriteArticleContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 15:02 <p>
 * interface :
 */

public class WriteArticleModel implements WriteArticleContract.AbsWriteArticleModel {

    @Override
    public Observable<UserBlogDataEntity> loadUserBlogInfo(String token) {
        return Api.getApiService(0).getUserBlogDataEntity(token).compose(RxSchedulers.<UserBlogDataEntity>io_main());
    }

    @Override
    public Observable<OnlyData> commitUserBlog(Map<String, String> options) {
        return Api.getApiService(0).commitUserArticles(options).compose(RxSchedulers.<OnlyData>io_main());
    }
}
