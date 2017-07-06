package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.AskQuestionContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.Map;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/1 - 16:55 <p>
 * interface :
 */

public class AskQuestionPresenter extends AskQuestionContract.AbsAskQuestionPresenter {

    @Override
    public void addUserQuestion(Map<String, String> map) {
        mModel.addUserQuestion(map).subscribe(new Action1<OnlyData>() {
            @Override
            public void call(OnlyData onlyData) {
                mView.showResult(onlyData);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("add User question data : " + throwable);
                mView.showResult(null);
            }
        }) ;
    }
}
