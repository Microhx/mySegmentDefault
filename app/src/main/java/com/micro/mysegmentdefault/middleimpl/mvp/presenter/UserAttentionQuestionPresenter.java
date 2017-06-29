package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 13:35 <p>
 * interface :
 */

public class UserAttentionQuestionPresenter extends BaseRefreshPresenter<BaseRefreshView,BaseRefreshModel> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<TagDetailQuestionEntity>() {
            @Override
            public void call(TagDetailQuestionEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.getCommonListDatas(startPages,entity.data.rows,entity.data.page);
                }else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("userAttentionQuestion presenter : " + throwable);
                mView.getRequestError(startPages);
            }
        });


    }
}
