package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.ArticleDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:24 <p>
 * interface :
 */

public class ArticleModel implements BaseRefreshModel<ArticleDataEntity> {

    @Override
    public Observable<ArticleDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        if (type == 0) {

            return Api.
                    getApiService(0).
                    getNewsBaseDataEntityList(channel, String.valueOf(startPages)).
                    compose(RxSchedulers.<ArticleDataEntity>io_main());

        } else if(type == Integer.MIN_VALUE) {
            return Api.
                    getApiService(0).
                    getUserZoneArticleDataEntity(channel, UserLogic.getUserToken(),startPages).
                    compose(RxSchedulers.<ArticleDataEntity>io_main());
        }

        return Api.
                getApiService(0).
                getNewsExtendsDataEntityList(channel, String.valueOf(startPages)).
                compose(RxSchedulers.<ArticleDataEntity>io_main());
    }
}
