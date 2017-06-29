package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 15:29 <p>
 * interface :
 */

public class SearchSubModel implements BaseRefreshModel<SearchDataEntity> {

    @Override
    public Observable<SearchDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        return Api.
                getApiService(0).
                getSearchDataEntity(channel,startPages , getType(type)).
                compose(RxSchedulers.<SearchDataEntity>io_main());
    }

    public String getType(int type) {
        switch (type) {
            default:
            case 0:return "";

            case 1:return "question";

            case 2:return "article";

            case 3:return "tag";

            case 4:return "user";
        }
    }

}
