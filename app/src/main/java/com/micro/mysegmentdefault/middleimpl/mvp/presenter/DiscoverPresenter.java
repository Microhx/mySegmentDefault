package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.middle.DiscoverContract;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 23:12 <p>
 * interface :
 */

public class DiscoverPresenter extends DiscoverContract.Presenter {

    @Override
    public void getRecommendActivityList() {
        mModel.getDiscoverDataEntity().subscribe(new Action1<DiscoverDataEntity>() {
            @Override
            public void call(DiscoverDataEntity discoverDataEntity) {
                mView.showDiscoverData(discoverDataEntity);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.onErrorWhenReceiveDiscoverData();
            }
        });
    }
}
