package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;
import com.micro.mysegmentdefault.middle.NewsDetailContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : 文章详细内容类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/15 - 21:08 <p>
 * interface :
 */

public class NewsDetailPresenter extends NewsDetailContract.DetailPresenter {

    @Override
    public void loadArticleDetailInfo(String articleId) {
        mModel.getNewsDataDetailDataEntity(articleId).subscribe(new Action1<NewsDetailDataEntity>() {
            @Override
            public void call(NewsDetailDataEntity newsDetailDataEntity) {
                if(null != newsDetailDataEntity) {
                    mView.showNewsDataDetailEntity(newsDetailDataEntity);
                }else {
                   mView.loadDataError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("loadArticleDetailInfo error:" + throwable);
                mView.loadDataError();
            }
        }) ;
    }


    @Override
    public void zanOperation(boolean isCancel, String newsId) {
        mModel.getZanOperationDataEntity(isCancel,newsId).subscribe(new Action1<BaseDataEntity>() {
            @Override
            public void call(BaseDataEntity baseDataEntity) {
                if(baseDataEntity!=null && baseDataEntity.status == 0) {
                    mView.zanOperationFinish(baseDataEntity.data);
                }else{
                    mView.zanOperationError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("zan error : " + throwable);
                mView.zanOperationError();
            }
        }) ;
    }


    @Override
    public void followOrCancelUser(boolean isCancel, String userId) {
        mModel.followOrCancelUser(isCancel,userId).subscribe(new Action1<FollowDataEntity>() {
            @Override
            public void call(FollowDataEntity entity) {
                if(entity != null) {
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
