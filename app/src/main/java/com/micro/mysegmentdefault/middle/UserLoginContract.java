package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserDataEntity;
import com.micro.mysegmentdefault.entity.UserLoginDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/10 - 15:47 <p>
 * interface :
 */

public interface UserLoginContract  {

    interface AbsLoginModel extends BaseModel{

        Observable<UserLoginDataEntity> userLogin(String phone , String password);

        Observable<OnlyData> userRegister(String name , String phone , String password);

        Observable<UserLoginDataEntity> userRegistercheck(String name,String phone ,String code , String password);

    }

    interface AbsLoginView extends BaseView {
        void showLoginResult(UserLoginDataEntity entity);

        void showUserRegisterCheck(UserLoginDataEntity entity);

        void showRegisterResult(OnlyData onlyData);


        Context getShowingActivity();
    }

    abstract class AbsLoginPresenter extends BasePresenter<AbsLoginView,AbsLoginModel> {
        public abstract void login(String phone , String password);
    }


}
