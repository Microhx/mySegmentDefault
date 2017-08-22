package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.UserCommentResultDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserCommentContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : 用户评论Presenter实现类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 17:07 <p>
 * interface :
 */

public class UserCommentPresenterImpl extends UserCommentContract.UserCommentPresenter {

    @Override
    public void addUserComment(String newsId, boolean isReply, String text) {
        mModel.addUserComment(newsId,isReply,text, UserLogic.getUserToken()).
               subscribe(new RxSubscriber<UserCommentResultDataEntity>(mContext,"") {
            @Override
            public void _onNext(UserCommentResultDataEntity userCommentResultDataEntity) {
                mView.showCommentResult(userCommentResultDataEntity);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("onRequest error : " + t );
                mView.showCommentResult(null);
            }
        }) ;


    }
}
