package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.ChannelDataEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 16:54 <p>
 * interface :
 */

public class ChannelChooseModel implements BaseRefreshModel<ChannelDataEntity> {

    @Override
    public Observable<ChannelDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Observable.create(new Observable.OnSubscribe<ChannelDataEntity>() {
            @Override
            public void call(Subscriber<? super ChannelDataEntity> subscriber) {
                ChannelDataEntity entity = new ChannelDataEntity();

                List<ChannelDataEntity.Item> list = new ArrayList<>();
                list.add(new ChannelDataEntity.Item("前端","1490000006201494")) ;
                list.add(new ChannelDataEntity.Item("后端","1490000006201495")) ;
                list.add(new ChannelDataEntity.Item("iOS","1490000006201496")) ;
                list.add(new ChannelDataEntity.Item("Android","1490000006201502")) ;
                list.add(new ChannelDataEntity.Item("安全","1490000006201507")) ;
                list.add(new ChannelDataEntity.Item("工具","1490000006201510")) ;
                list.add(new ChannelDataEntity.Item("程序员","1490000006201514")) ;
                list.add(new ChannelDataEntity.Item("行业","1490000006218048")) ;

                entity.data = list;
                entity.status = 0 ;

                subscriber.onNext(entity);
            }
        });
    }
}
