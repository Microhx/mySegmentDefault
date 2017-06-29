package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.middle.MultipleSearchContract;

import java.util.List;

import rx.Observable;

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
    public Observable<List<String>> loadUserHistorySearch() {
        return Observable.just("java","php","android","ios","javascript","NoSql").buffer(4);
    }
}
