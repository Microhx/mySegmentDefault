package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 15:56 <p>
 * interface :
 */

public interface QuestionContract {

    interface  QuestionDetailModel extends BaseModel{
        Observable<QuestionDetailDataEntity> getQuestionDetailDataEntity(String questionId);

        Observable<BaseDataEntity> getQuestionDetailFollow(boolean isCancel,String questionId);
    }

    interface QuestionDetailView extends BaseView {
        void showQuestionDetailEntity(QuestionDetailDataEntity entity);

        void showError() ;

        void showQuestionDetailFollow(boolean result , String number);
    }

    abstract class Presenter extends BasePresenter<QuestionDetailView,QuestionDetailModel> {

        public abstract void loadQuestionAnswer(String questionId);

        public abstract void loadQuestionFollow(boolean isCancel , String questionId);

    }

}
