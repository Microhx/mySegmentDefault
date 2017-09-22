package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import android.util.Log;

import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.ArticleDetailContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 14:04 <p>
 * interface :
 */

public class ArticleDetailPresenter extends ArticleDetailContract.DetailPresenter {

    @Override
    public void loadArticleDetailInfo(String newsId) {

        mModel.getArticleDetailDataEntity(newsId).subscribe(new Action1<ArticleDetailEntity>() {
            @Override
            public void call(ArticleDetailEntity articleDetailEntity) {
                if (null != articleDetailEntity) {
                    mView.showArticleDetailEntity(articleDetailEntity);
                } else {
                    mView.loadDataError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("article detail load error : " + throwable);
                mView.loadDataError();
            }
        });
    }

    public void zanOperation(boolean selected, String mNewsId) {
        mModel.zanOperation(selected,mNewsId, UserLogic.getUserToken()).subscribe(new Action1<BaseDataEntity>() {
            @Override
            public void call(BaseDataEntity entity) {
                mView.zanOperation(entity);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("article zan error : " + throwable);
                mView.zanOperation(null);
            }
        });
    }


    public void followOrCancelUser(boolean isCancel, String userId) {
        mModel.followOrCancelUser(isCancel,userId).subscribe(new Action1<FollowDataEntity>() {
            @Override
            public void call(FollowDataEntity entity) {
                if(entity != null && entity.data != null ) {
                    mView.followUserResult(entity.status == 0 , entity.data.id);
                }else{
                    mView.followUserResult(false,null);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("---followOrCancelUser error : " + throwable);

                mView.followUserResult(false,null);
            }
        });

    }
}
