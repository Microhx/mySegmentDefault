package com.micro.mysegmentdefault.middleimpl.mvp.model;

import android.content.Context;

import com.micro.mysegmentdefault.middle.MultipleSearchContract;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.SearchKeyWordsUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author : micro_hx <p>
 * desc : 获取搜索历史 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 11:31 <p>
 * interface :
 */

public class MultipleSearchModel implements MultipleSearchContract.AbsMultipleSearchModel {

    //TODO　just do IT test
    @Override
    public Observable<List<String>> loadUserHistorySearch(final Context ctx) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(SearchKeyWordsUtils.getSearchHistoryWords(ctx));
            }
        }).compose(RxSchedulers.<List<String>>io_main());
    }
}
