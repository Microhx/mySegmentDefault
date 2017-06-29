package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserPublishToutiaoContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 19:32 <p>
 * interface :
 */

public class UserPublishToutiaoPresenter extends UserPublishToutiaoContract.AbsUserPublishToutiaoPresenter {


    @Override
    public void updateToutiao(String url, String title, String type, String description) {
        mModel.updateToutiao(url, title, type, description, UserLogic.getUserToken()).
               subscribe(new RxSubscriber<OnlyData>(mView.getContext(),"") {
            @Override
            public void _onNext(OnlyData onlyData) {
                mView.updateToutiaoResult(onlyData);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("updateToutiao error : " + t);
                mView.updateToutiaoResult(null);
            }
        });
    }
}

