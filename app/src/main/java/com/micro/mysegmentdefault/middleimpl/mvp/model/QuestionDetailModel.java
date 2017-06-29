package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.QuestionContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:04 <p>
 * interface :
 */

public class QuestionDetailModel implements QuestionContract.QuestionDetailModel {

    @Override
    public Observable<QuestionDetailDataEntity> getQuestionDetailDataEntity(String questionId) {
        return Api.
                getApiService(0).
                getQuestionDetailData(questionId, UserLogic.getUserToken()).
                compose(RxSchedulers.<QuestionDetailDataEntity>io_main());
    }


    @Override
    public Observable<BaseDataEntity> getQuestionDetailFollow(boolean isCancel, String questionId) {
        if (isCancel) {
            return Api.
                    getApiService(0).
                    userCancelFollowQuestionDataEntity(questionId, UserLogic.getUserToken()).
                    compose(RxSchedulers.<BaseDataEntity>io_main());
        }

        return Api.
                getApiService(0).
                userFollowQuestionDataEntity(questionId, UserLogic.getUserToken()).
                compose(RxSchedulers.<BaseDataEntity>io_main());
    }
}
