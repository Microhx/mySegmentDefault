package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:16 <p>
 * interface :
 */

public class UserTagPresenter extends BaseRefreshPresenter<BaseRefreshView<TagDataEntity.Item>,BaseRefreshModel<TagDataEntity>> {

    @Override
    public void getCommonListDatas(final int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<TagDataEntity>() {

            @Override
            public void call(TagDataEntity tagDataEntity) {
                if (null != tagDataEntity && null != tagDataEntity.data) {
                    List<TagDataEntity.Item> itemList = tagDataEntity.data.rows;
                    PageEntity pageEntity = tagDataEntity.data.page;
                    mView.getCommonListDatas(startPages, itemList, pageEntity);
                } else {
                    if(type == 0) {
                        //本地不存在时 直接返回空白页面
                        mView.getCommonListDatas(startPages, new ArrayList<TagDataEntity.Item>(), null);

                    }else {
                        mView.getRequestError(startPages);
                    }
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("UserTagPresenter,get data error:" + throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
