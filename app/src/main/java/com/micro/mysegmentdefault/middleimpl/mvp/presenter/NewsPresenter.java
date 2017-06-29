package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.entity.NewsDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
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

public class NewsPresenter extends BaseRefreshPresenter<BaseRefreshView<NewsDataEntity.Item>,BaseRefreshModel<NewsDataEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel,final int startPages) {

        mModel.getCommentListDatas(type,channel,startPages).
                subscribe(new Action1<NewsDataEntity>() {
                    @Override
                    public void call(NewsDataEntity newsDataEntity) {
                        if(null != newsDataEntity && null!=newsDataEntity.data) {
                            List<NewsDataEntity.Item> itemList = newsDataEntity.data.rows;
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
