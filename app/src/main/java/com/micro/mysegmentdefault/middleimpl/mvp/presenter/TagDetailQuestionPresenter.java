package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:51 <p>
 * interface :
 */

public class TagDetailQuestionPresenter extends
        BaseRefreshPresenter<BaseRefreshView<TagDetailQuestionEntity.Item>,BaseRefreshModel<TagDetailQuestionEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<TagDetailQuestionEntity>() {
            @Override
            public void call(TagDetailQuestionEntity tagDetailQuestionEntity) {
                if(null != tagDetailQuestionEntity && tagDetailQuestionEntity.status == 0) {
                    List<TagDetailQuestionEntity.Item> itemList = tagDetailQuestionEntity.data.rows;
                    PageEntity page = tagDetailQuestionEntity.data.page;

                    mView.getCommonListDatas(startPages,itemList,page);

                }else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("TagDetailQuestion error:" + throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
