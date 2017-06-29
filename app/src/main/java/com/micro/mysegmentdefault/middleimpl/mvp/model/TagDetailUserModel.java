package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.entity.TagDetailUserEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 17:06 <p>
 * interface :
 */

public class TagDetailUserModel implements BaseRefreshModel<TagDetailUserEntity> {

    @Override
    public Observable<TagDetailUserEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getTagDetailUserEntity(channel).
                compose(RxSchedulers.<TagDetailUserEntity>io_main());
    }
}
