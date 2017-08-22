package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.UserCommentResultDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : 用户评论 契约 接口<p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 17:04 <p>
 * interface :
 */

public interface UserCommentContract {
    interface UserCommentModel extends BaseModel {
        Observable<UserCommentResultDataEntity> addUserComment(String newsId , boolean isReply , String text , String token);

    }

    interface UserCommentView extends BaseView {
        void showCommentResult(UserCommentResultDataEntity entity);

    }

    abstract class UserCommentPresenter extends BasePresenter<UserCommentView,UserCommentModel> {
        public abstract void addUserComment(String newsId , boolean isReply , String text) ;
    }

}
