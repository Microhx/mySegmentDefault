package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.middle.UserNewNoteContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : 用户新增笔记<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 9:42 <p>
 * interface :
 */

public class UserNewNotePresenter extends UserNewNoteContract.AbsNewNotePresenter {

    @Override
    public void addNewNote(String noteTitle, String noteContent, String noteType,boolean isPrivate) {
        mModel.addNewNote(noteTitle, noteContent, noteType,isPrivate).subscribe(new RxSubscriber<NoteDetailDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(NoteDetailDataEntity entity) {
                mView.addNewNoteResult(null != entity && entity.getStatus() == 0);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("addNewNote error : " + t);
                mView.addNewNoteResult(false);
            }
        });

    }
}

