package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.NewsDataEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:24 <p>
 * interface :
 */

public class NewsModel implements BaseRefreshModel<NewsDataEntity> {

    @Override
    public Observable<NewsDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        if (type == 0) {
            return Api.
                    getApiService(0).
                    getNewsBaseDataEntityList(channel, String.valueOf(startPages)).
                    compose(RxSchedulers.<NewsDataEntity>io_main());
        }

        return Api.
                getApiService(0).
                getNewsExtendsDataEntityList(channel, String.valueOf(startPages)).
                compose(RxSchedulers.<NewsDataEntity>io_main());
    }
}
