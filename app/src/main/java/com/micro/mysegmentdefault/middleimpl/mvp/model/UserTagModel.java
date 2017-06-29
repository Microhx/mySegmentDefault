package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.FileUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:33 <p>
 * interface :
 */

public class UserTagModel implements BaseRefreshModel<TagDataEntity> {

    @Override
    public Observable<TagDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        if (type == 0) {
            return Observable.create(new Observable.OnSubscribe<TagDataEntity>() {
                @Override
                public void call(Subscriber<? super TagDataEntity> subscriber) {
                    TagDataEntity tagDataEntity = FileUtils.getUserTagDataEntity();
                    subscriber.onNext(tagDataEntity);
                }
            }).compose(RxSchedulers.<TagDataEntity>io_main());
        }

        return Api.
                getApiService(0).
                getHotsTagDataEntityList().
                compose(RxSchedulers.<TagDataEntity>io_main());
    }
}
