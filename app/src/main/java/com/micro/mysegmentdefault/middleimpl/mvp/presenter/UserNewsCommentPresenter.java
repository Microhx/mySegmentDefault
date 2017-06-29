package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.AbsUserNewsCommentView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.middle.model.AbsNewsCommentModel;
import com.micro.mysegmentdefault.middle.presenter.AbsNewsCommentPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : 评论获取 presenter <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 13:39 <p>
 * interface :
 */

public class UserNewsCommentPresenter extends AbsNewsCommentPresenter<AbsUserNewsCommentView<NewsCommentDataEntity.CommentItem>, AbsNewsCommentModel<NewsCommentDataEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<NewsCommentDataEntity>() {
            @Override
            public void call(NewsCommentDataEntity entity) {
                if (null != entity && entity.status == 0) {
                    PageEntity page = entity.data.page;
                    List<NewsCommentDataEntity.CommentItem> rows = entity.data.rows;
                    mView.getCommonListDatas(startPages, rows, page);
                } else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("getComment error : " + throwable);
                mView.getRequestError(startPages);
            }
        });
    }


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

}
