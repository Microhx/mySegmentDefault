package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.middle.NoteDetailContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:58 <p>
 * interface :
 */

public class NoteDetailPresenter extends NoteDetailContract.Presenter {

    @Override
    public void loadingNoteDetail(String noteId) {
        mModel.getNoteDetail(noteId).subscribe(new Action1<NoteDetailDataEntity>() {
            @Override
            public void call(NoteDetailDataEntity noteDetailDataEntity) {
                if (null != noteDetailDataEntity) {
                    mView.loadingNoteDetail(noteDetailDataEntity);
                } else {
                    mView.showErrorLoading();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("loading note detail error: " + throwable);
                mView.showErrorLoading();
            }
        });
    }

    @Override
    public void createOrCancelBranch(String noteId) {
        mModel.createOrCancelBranch(noteId).subscribe(new Action1<NoteDetailDataEntity>() {
            @Override
            public void call(NoteDetailDataEntity noteEntity) {
                if (null != noteEntity && noteEntity.getStatus() == 0) {
                    mView.createOrCancelBranch(true);
                } else {
                    mView.createOrCancelBranch(false);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("create Or cancel branch error: " + throwable);
                mView.createOrCancelBranch(false);
            }
        });
    }
}
