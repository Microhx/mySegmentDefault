package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserBlogDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.WriteArticleContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.Map;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 15:00 <p>
 * interface :
 */

public class WriteArticlePresenter extends WriteArticleContract.AbsWritePresenter {
    @Override
    public void loadUserBlogInfo() {
        mModel.loadUserBlogInfo(UserLogic.getUserToken()).subscribe(new Action1<UserBlogDataEntity>() {
            @Override
            public void call(UserBlogDataEntity dataEntity) {
                mView.showUserBlogInfo(dataEntity);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("loadUserBlogInfo:" + throwable);
                mView.showUserBlogInfo(null);
            }
        });
    }

    @Override
    public void commitUserBlog(Map<String, String> options) {
        mModel.commitUserBlog(options).
               subscribe(new RxSubscriber<OnlyData>(mView.getContext(),"") {
            @Override
            public void _onNext(OnlyData data) {
                mView.showUserCommitBlog(data);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("commitUserBlog error:"+t);
                mView.showUserCommitBlog(null);
            }
        }) ;
    }
}
