package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.NoteDetailContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 18:01 <p>
 * interface :
 */

public class NoteDetailModel implements NoteDetailContract.NoteDetailModel {


    @Override
    public Observable<NoteDetailDataEntity> getNoteDetail(String noteId) {
        return Api.
                getApiService(0).
                getNoteDetailData(noteId, UserLogic.getUserToken()).
                compose(RxSchedulers.<NoteDetailDataEntity>io_main());
    }

    @Override
    public Observable<NoteDetailDataEntity> createOrCancelBranch(String noteId) {
        return Api.
                getApiService(0).
                userForkNoteData(noteId,UserLogic.getUserToken()).
                compose(RxSchedulers.<NoteDetailDataEntity>io_main());
    }
}
