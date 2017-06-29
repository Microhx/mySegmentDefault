package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 23:24 <p>
 * interface :
 */

public class UserSearchTagModel implements BaseRefreshModel<TagDataEntity> {

    @Override
    public Observable<TagDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getSearchTagDataEntityList(channel,String.valueOf(startPages),"tag").
                compose(RxSchedulers.<TagDataEntity>io_main());
    }
}
