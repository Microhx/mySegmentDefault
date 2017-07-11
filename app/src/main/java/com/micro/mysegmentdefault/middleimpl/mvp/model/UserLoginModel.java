package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserLoginDataEntity;
import com.micro.mysegmentdefault.middle.UserLoginContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/10 - 17:00 <p>
 * interface :
 */

public class UserLoginModel implements UserLoginContract.AbsLoginModel{

    @Override
    public Observable<UserLoginDataEntity> userLogin(String phone, String password) {
        return Api.getApiService(0).userLogin(phone,password).compose(RxSchedulers.<UserLoginDataEntity>io_main());
    }


    @Override
    public Observable<OnlyData> userRegister(String name, String phone, String password) {
        return Api.getApiService(0).userRegister(name,phone,password).compose(RxSchedulers.<OnlyData>io_main());
    }


    @Override
    public Observable<UserLoginDataEntity> userRegistercheck(String name, String phone, String code, String password) {
        return  Api.getApiService(0).userRegisterCheck(name,phone,code,password).compose(RxSchedulers.<UserLoginDataEntity>io_main());
    }
}
