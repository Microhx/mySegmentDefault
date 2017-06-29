package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.UserDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 9:27 <p>
 * interface :
 */

public interface UserZoneContract {

    interface UserZoneModel extends BaseModel {
        Observable<UserDataEntity> getUserZoneData(String username);
    }

    interface UserZoneView extends BaseView{
        void loadUserZoneData(UserDataEntity entity);
        void loadUserZoneError();
    }

    abstract class Presenter extends BasePresenter<UserZoneView,UserZoneModel> {
        public abstract void loadUserZoneBaseInfo(String username);
    }
}
