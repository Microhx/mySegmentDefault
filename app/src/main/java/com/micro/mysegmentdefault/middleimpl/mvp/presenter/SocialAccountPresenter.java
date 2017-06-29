package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.SocialAccountBindDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.SocialAccountShowContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 22:18 <p>
 * interface :
 */

public class SocialAccountPresenter extends SocialAccountShowContract.AbsSocialAccountPresenter {


    @Override
    public void loadSocialAccountInfo() {
        mModel.loadSocialAccountInfo(UserLogic.getUserToken()).subscribe(new Action1<SocialAccountBindDataEntity>() {
            @Override
            public void call(SocialAccountBindDataEntity entity) {
                mView.showSocialAccountResult(entity);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("social account info error : " + throwable);
                mView.showSocialAccountResult(null);
            }
        });

    }

    @Override
    public void updateSocialAccountInfo(String type, boolean isClose) {
        mModel.updateSocialAccountInfo(type,isClose,UserLogic.getUserToken()).subscribe(new Action1<OnlyData>() {
            @Override
            public void call(OnlyData entity) {
                mView.updateSocialAccountInfo(entity != null && entity.status == 0);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("updateSocialAccountInfo:"+throwable);
                mView.updateSocialAccountInfo(false);
            }
        });
    }
}
