package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.NewCollectionDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 22:51 <p>
 * interface :
 */

public interface UserAddNewCollectContract {

    interface AbsNewCollectView extends BaseView {

        Context getContext();

        void addNewCollectSuccess(String id , String title , String isPrivate );

        void addNewCollectError();

    }

    interface AbsNewCollectModel extends BaseModel {

        Observable<NewCollectionDataEntity> addNewCollect(String title , String desc, boolean isPrivate , String token);

    }

    abstract class AbsNewCollectPresenter extends BasePresenter<AbsNewCollectView,AbsNewCollectModel> {
        public abstract void addNewCollect(String mCollectTitle , String mCollectDes , boolean isPrivate);
    }
}
