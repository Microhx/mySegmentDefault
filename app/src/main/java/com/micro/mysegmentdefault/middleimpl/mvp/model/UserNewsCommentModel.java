package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.model.AbsNewsCommentModel;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 13:47 <p>
 * interface :
 */

public class UserNewsCommentModel implements AbsNewsCommentModel<NewsCommentDataEntity> {

    @Override
    public Observable<NewsCommentDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getUserCommentDataEntity(getType(type),channel,String.valueOf(startPages), UserLogic.getUserToken()).
                compose(RxSchedulers.<NewsCommentDataEntity>io_main());
    }

    private String getType(int type) {
        if(type == 1) return "note";
        if(type == 0) return "news";
        return  "article";
    }

    @Override
    public Observable<BaseDataEntity> getZanOperationDataEntity(String type , boolean isCancel, String newsId) {
        if(isCancel) {
            return Api.getApiService(0).userDisLikeNewsDataEntity(type,newsId,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else {
            return Api.getApiService(0).userLikeNewsDataEntity(type,newsId,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }

    }

}
