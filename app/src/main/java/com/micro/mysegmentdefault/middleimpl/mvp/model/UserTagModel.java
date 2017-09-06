package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.FileUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

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
                map(new Func1<TagDataEntity, TagDataEntity>() {

                @Override
                public TagDataEntity call(TagDataEntity tagDataEntity) {

                TagDataEntity localDataEntity = FileUtils.getUserTagDataEntity();
                if(null != localDataEntity && !CommonUtils.collectionIsNull(localDataEntity.data.rows)) {
                    List<TagDataEntity.Item> localItemList = localDataEntity.data.rows;

                    if(null != tagDataEntity && !CommonUtils.collectionIsNull(tagDataEntity.data.rows)) {
                        List<TagDataEntity.Item> remoteList = tagDataEntity.data.rows;

                        for(TagDataEntity.Item remoteItem : remoteList) {
                            remoteItem.isFollowed = localItemList.contains(remoteItem);
                        }
                    }
                }

                return tagDataEntity;
            }
        }).compose(RxSchedulers.<TagDataEntity>io_main());
    }
}
