package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
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
}
