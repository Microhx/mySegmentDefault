package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.middle.MultipleSearchContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 11:27 <p>
 * interface :
 */

public class MultipleSearchPresenter extends MultipleSearchContract.AbsMutipleSearchPresenter {

    @Override
    public void loadUserHistorySearch() {
        mModel.loadUserHistorySearch(mView.getContext()).
                subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                mView.showHistorySearchResult(strings);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("load user history error : " + throwable);
                mView.showHistorySearchResult(null);
            }
        });
    }
}
