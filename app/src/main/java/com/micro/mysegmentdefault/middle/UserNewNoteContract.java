package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 9:26 <p>
 * interface :
 */

public interface UserNewNoteContract {


    interface AbsNewNoteView extends BaseView{
        void addNewNoteResult(boolean result);

        Context getContext();

    }

    interface AbsNewNoteModel extends BaseModel {
        Observable<NoteDetailDataEntity> addNewNote(String noteTitle,String noteContent,String noteType , boolean isPrivate);
    }

    abstract class AbsNewNotePresenter extends BasePresenter<AbsNewNoteView,AbsNewNoteModel> {

        /**
         * add new note byUser
         * @param noteTitle
         * @param noteContent
         * @param noteType C/C++/PHP/Java ...
         */
        public abstract void addNewNote(String noteTitle , String noteContent, String noteType, boolean isPrivate);

    }


}
