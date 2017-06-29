package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 13:38 <p>
 * interface :
 */

public class UserAttentionQuestionModel implements BaseRefreshModel<TagDetailQuestionEntity> {

    @Override
    public Observable<TagDetailQuestionEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                userAttentionQuestionDataEntity(UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<TagDetailQuestionEntity>io_main());
    }
}
