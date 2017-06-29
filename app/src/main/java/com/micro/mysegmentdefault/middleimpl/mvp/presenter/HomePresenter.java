package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 17:18 <p>
 * interface :
 */

public class HomePresenter extends BaseRefreshPresenter<BaseRefreshView<HomeDataEntity.Item>,BaseRefreshModel<HomeDataEntity>>{

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).
              subscribe(new Action1<HomeDataEntity>() {
            @Override
            public void call(HomeDataEntity homeDataEntity) {
                if(null != homeDataEntity && null!=homeDataEntity.data) {
                    List<HomeDataEntity.Item> itemList = homeDataEntity.data.rows;
                    PageEntity pageEntity = homeDataEntity.data.page;
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
