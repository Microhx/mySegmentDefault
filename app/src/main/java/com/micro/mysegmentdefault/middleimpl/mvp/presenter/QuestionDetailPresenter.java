package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;
import com.micro.mysegmentdefault.middle.QuestionContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:01 <p>
 * interface :
 */

public class QuestionDetailPresenter extends QuestionContract.Presenter {


    @Override
    public void loadQuestionAnswer(String questionId) {
        mModel.getQuestionDetailDataEntity(questionId).subscribe(new Action1<QuestionDetailDataEntity>() {
            @Override
            public void call(QuestionDetailDataEntity questionDetailDataEntity) {
                if (null != questionDetailDataEntity) {
                    mView.showQuestionDetailEntity(questionDetailDataEntity);
                } else {
                    mView.showError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("Question load error : " + throwable);
                mView.showError();
            }
        });
    }


    @Override
    public void loadQuestionFollow(boolean isCancel, String questionId) {
        mModel.getQuestionDetailFollow(isCancel, questionId).subscribe(new Action1<BaseDataEntity>() {
            @Override
            public void call(BaseDataEntity entity) {
                if (null != entity && entity.status == 0) {
                    mView.showQuestionDetailFollow(true, entity.data);
                } else {
                    mView.showQuestionDetailFollow(false, "0");
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("load question error : " + throwable);
                mView.showQuestionDetailFollow(false, "0");
            }
        });
    }
}
