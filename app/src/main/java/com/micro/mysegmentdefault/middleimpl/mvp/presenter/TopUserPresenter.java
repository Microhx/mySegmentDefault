package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.middle.TopUserContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 23:01 <p>
 * interface :
 */

public class TopUserPresenter extends TopUserContract.Presenter {

    @Override
    public void loadTopUserInfo() {
        mModel.getTopUserEntity().subscribe(new Action1<TopUserEntity>() {
            @Override
            public void call(TopUserEntity entity) {
                if (null != entity) {
                    mView.showTopUserEntity(entity);
                } else {
                    mView.onLoadingDataError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("get top User error : " + throwable);
                mView.onLoadingDataError();
            }
        });
    }
}
