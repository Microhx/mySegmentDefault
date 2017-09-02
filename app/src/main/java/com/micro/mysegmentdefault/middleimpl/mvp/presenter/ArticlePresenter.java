package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.ArticleDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:22 <p>
 * interface :
 */

public class ArticlePresenter extends BaseRefreshPresenter<BaseRefreshView<ArticleDataEntity.Item>,BaseRefreshModel<ArticleDataEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel,final int startPages) {

        mModel.getCommentListDatas(type,channel,startPages).
                subscribe(new Action1<ArticleDataEntity>() {
                    @Override
                    public void call(ArticleDataEntity newsDataEntity) {
                        if(null != newsDataEntity && null!=newsDataEntity.data) {
                            List<ArticleDataEntity.Item> itemList = newsDataEntity.data.rows;
                            PageEntity pageEntity = newsDataEntity.data.page;
                            mView.getCommonListDatas(startPages,itemList,pageEntity);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.d("homePresenter,get data error:" + throwable);
                        mView.getRequestError(startPages);
                    }
                });
    }
}
