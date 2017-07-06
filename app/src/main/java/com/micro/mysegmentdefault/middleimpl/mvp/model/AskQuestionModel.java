package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.AskQuestionContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/1 - 16:58 <p>
 * interface :
 */

public class AskQuestionModel implements AskQuestionContract.AbsAskQuestionModel {

    @Override
    public Observable<OnlyData> addUserQuestion(Map<String, String> map) {
        return Api.
                getApiService(0).
                addUserNewQuestionDataEntity(map).
                compose(RxSchedulers.<OnlyData>io_main());
    }
}
