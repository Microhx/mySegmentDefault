package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserNewNoteContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 9:48 <p>
 * interface :
 */

public class UserNewNoteModel implements UserNewNoteContract.AbsNewNoteModel {

    @Override
    public Observable<NoteDetailDataEntity> addNewNote(String noteTitle, String noteContent, String noteType,boolean isPrivate) {
        return Api.
               getApiService(0).
               addUserNewNoteDataEntity(noteTitle,noteContent,noteType,isPrivate?"0":"1", UserLogic.getUserToken()).
               compose(RxSchedulers.<NoteDetailDataEntity>io_main());
    }
}
