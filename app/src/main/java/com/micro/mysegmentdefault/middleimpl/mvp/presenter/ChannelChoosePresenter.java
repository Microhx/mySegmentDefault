package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.ChannelDataEntity;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 16:44 <p>
 * interface :
 */

public class ChannelChoosePresenter extends BaseRefreshPresenter<BaseRefreshView,BaseRefreshModel> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<ChannelDataEntity>() {
            @Override
            public void call(ChannelDataEntity entity) {
                if(null != entity){
                    mView.getCommonListDatas(startPages,entity.data,null);
                }else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.getRequestError(startPages);
            }
        });

    }
}
