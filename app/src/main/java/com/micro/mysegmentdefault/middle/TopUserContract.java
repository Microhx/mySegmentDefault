package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.TopUserEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 22:51 <p>
 * interface :
 */

public interface TopUserContract {


    interface TopUserModel extends BaseModel {

        Observable<TopUserEntity> getTopUserEntity();

    }

    interface TopUserView extends BaseView {
        void showTopUserEntity(TopUserEntity entity);

        void onLoadingDataError();
    }

    abstract  class  Presenter extends BasePresenter<TopUserView,TopUserModel> {
        public abstract void loadTopUserInfo();
    }
}
