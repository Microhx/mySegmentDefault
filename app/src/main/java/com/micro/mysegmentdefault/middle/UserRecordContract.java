package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.UserRecordEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 11:25 <p>
 * interface :
 */

public interface UserRecordContract {

    interface AbsUserRecordModel extends BaseModel{
        Observable<UserRecordEntity> loadingUserRecordInfo(String username);
    }

    interface AbsUserRecordView extends BaseView {
        void loadUserRecordInfo(UserRecordEntity.UserRecordItem data);
        void loadUserRecordInfoError();
    }

    abstract class Presenter extends BasePresenter<AbsUserRecordView,AbsUserRecordModel> {
        public abstract void loadingUserRecordInfo(String username);
    }
}
