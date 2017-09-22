package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.ArticleDetailContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 14:09 <p>
 * interface :
 */

public class ArticleDetailModel implements ArticleDetailContract.ArticleDetailModel {

    @Override
    public Observable<ArticleDetailEntity> getArticleDetailDataEntity(String newsId) {
        return Api.
                getApiService(0).
                getArticleDetailData(newsId, UserLogic.getUserToken()).
                compose(RxSchedulers.<ArticleDetailEntity>io_main());
    }


    @Override
    public Observable<BaseDataEntity> zanOperation(boolean isCancel, String newsId, String token) {
        if(isCancel) {
            return Api.getApiService(0).userDisLikeNewsDataEntity("article",newsId,token).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else {
            return Api.getApiService(0).userLikeNewsDataEntity("article",newsId,token).compose(RxSchedulers.<BaseDataEntity>io_main());
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
