package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserLoginDataEntity;
import com.micro.mysegmentdefault.middle.UserLoginContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/10 - 15:49 <p>
 * interface :
 */

public class UserLoginPresenter extends UserLoginContract.AbsLoginPresenter {

    @Override
    public void login(String phone, String password) {

        mModel.userLogin(phone, password).
               subscribe(new RxSubscriber<UserLoginDataEntity>(mView.getShowingActivity(),"") {
            @Override
            public void _onNext(UserLoginDataEntity userLoginDataEntity) {
                mView.showLoginResult(userLoginDataEntity);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("user Login error : " +t);
                mView.showLoginResult(null);
            }
        });
    }


    public void register(String name , String phone , String password) {
        mModel.userRegister(name,phone,password).subscribe(new RxSubscriber<OnlyData>(mView.getShowingActivity(),"") {
            @Override
            public void _onNext(OnlyData onlyData) {
                mView.showRegisterResult(onlyData);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("register data error : " + t);
                mView.showRegisterResult(null);
            }
        }) ;

    }


    public void checkUserPhone(String name , String phone , String code , String password) {
        mModel.userRegistercheck(name,phone,code,password).subscribe(new RxSubscriber<UserLoginDataEntity>(mView.getShowingActivity(),"") {
            @Override
            public void _onNext(UserLoginDataEntity entity) {
                mView.showUserRegisterCheck(entity);
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("check user phone error : " + t);
                mView.showUserRegisterCheck(null);
            }

        }) ;
    }


}
