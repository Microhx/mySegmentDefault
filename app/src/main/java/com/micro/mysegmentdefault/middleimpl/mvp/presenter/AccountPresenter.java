package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.ThirdPlatformDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.AccountSettingContract;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 22:45 <p>
 * interface :
 */

public class AccountPresenter extends AccountSettingContract.AbsAccountPresenter {

    @Override
    public void loadUserAccountInfo() {

        Observable.zip(mModel.getAccountDataEntity(UserLogic.getUserUrl(), UserLogic.getUserToken()),
                mModel.getThirdPlatformDataEntity(UserLogic.getUserToken()),
                new Func2<AccountDataEntity, ThirdPlatformDataEntity, D<AccountDataEntity, ThirdPlatformDataEntity>>() {
                    @Override
                    public D<AccountDataEntity, ThirdPlatformDataEntity> call(AccountDataEntity accountDataEntity, ThirdPlatformDataEntity thirdPlatformDataEntity) {
                        D d = new D<>();
                        d.one = accountDataEntity;
                        d.two = thirdPlatformDataEntity;
                        return d;
                    }
                }).
                compose(RxSchedulers.<D<AccountDataEntity, ThirdPlatformDataEntity>>io_main()).
                subscribe(new Action1<D<AccountDataEntity, ThirdPlatformDataEntity>>() {
                    @Override
                    public void call(D<AccountDataEntity, ThirdPlatformDataEntity> entityD) {
                        if (null != entityD) {
                            mView.showAccountDataEntity(entityD.one);
                            mView.showThirdPlatformDataEntity(entityD.two);
                        } else {
                            mView.showDataError();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.d("loadUserAccountInfo error : " + throwable);
                        mView.showDataError();
                    }
                });
    }


    private static class D<One, Two> {
        public One one;
        public Two two;
    }
}
