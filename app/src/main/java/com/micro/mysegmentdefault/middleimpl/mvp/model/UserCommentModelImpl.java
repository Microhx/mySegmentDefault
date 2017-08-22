package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.UserCommentResultDataEntity;
import com.micro.mysegmentdefault.middle.UserCommentContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 17:09 <p>
 * interface :
 */

public class UserCommentModelImpl implements UserCommentContract.UserCommentModel {

    @Override
    public Observable<UserCommentResultDataEntity> addUserComment(String newsId, boolean isReply, String text, String token) {

        if(isReply) {
          return Api.getApiService(0).addUserNewsReplyComment(newsId,text,token).compose(RxSchedulers.<UserCommentResultDataEntity>io_main());
        }
        return Api.getApiService(0).addUserNewsComment(newsId,text,token).compose(RxSchedulers.<UserCommentResultDataEntity>io_main());
    }
}
