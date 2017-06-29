package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:54 <p>
 * interface :
 */

public interface NoteDetailContract {

    interface NoteDetailModel extends BaseModel {
        Observable<NoteDetailDataEntity> getNoteDetail(String noteId) ;

        Observable<NoteDetailDataEntity> createOrCancelBranch(String noteId);

    }

    interface NoteDetailView extends BaseView {
        void loadingNoteDetail(NoteDetailDataEntity entity) ;
        void showErrorLoading();

        void createOrCancelBranch(boolean result);

    }

    abstract class Presenter extends BasePresenter<NoteDetailView,NoteDetailModel>{

        public abstract void loadingNoteDetail(String noteId);


        public abstract void createOrCancelBranch(String noteId);


    }
}
