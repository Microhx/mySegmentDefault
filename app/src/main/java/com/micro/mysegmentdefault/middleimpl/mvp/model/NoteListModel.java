package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : 推荐笔记列表<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:01 <p>
 * interface :
 */

public class NoteListModel implements BaseRefreshModel<NoteDataEntity> {

    @Override
    public Observable<NoteDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        if(type == 0) {  //获取推荐
            return Api.
                    getApiService(0).
                    getNoteListEntityData(String.valueOf(startPages)).
                    compose(RxSchedulers.<NoteDataEntity>io_main());

        }else {  //获取自己的
            return Api.
                    getApiService(0).
                    getNoteListEntityData(String.valueOf(startPages), UserLogic.getUserToken()).
                    compose(RxSchedulers.<NoteDataEntity>io_main());
        }
    }

}
