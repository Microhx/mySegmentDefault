package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.model.AbsUserCollectModel;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 15:26 <p>
 * interface :
 */

public class UserCollectModel implements AbsUserCollectModel<UserCollectEntity> {

    @Override
    public Observable<UserCollectEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getUserCollectDataEntity(channel,String.valueOf(startPages), UserLogic.getUserToken()).
                compose(RxSchedulers.<UserCollectEntity>io_main());
    }

    @Override
    public Observable<BaseDataEntity> userAddCollectDataEntity(String newsId, int tagType, Map<String, String> collectIds,String token) {
        return Api.getApiService(0).
                addUserCollectionDataEntity(getTargetType(tagType), newsId,collectIds,token).
                compose(RxSchedulers.<BaseDataEntity>io_main());
    }


    private String getTargetType(int type) {
        if(type == 0) return  "user";     //用户 关注
        if(type == 1) return "article" ;  //文章 收藏
        if(type == 2) return "question";  //问题 收藏
        if(type == 3) return "note";      //笔记 收藏
        return "";
    }




}
