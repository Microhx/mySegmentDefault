package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.ThirdPlatformDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 22:40 <p>
 * interface :
 */

public interface AccountSettingContract {

    interface AbsAccountModel extends BaseModel {

        Observable<AccountDataEntity> getAccountDataEntity(String userUrl , String token);

        Observable<ThirdPlatformDataEntity> getThirdPlatformDataEntity(String token);

    }

    interface AbsAccountView extends BaseView {
        void showAccountDataEntity(AccountDataEntity entity);
        void showThirdPlatformDataEntity(ThirdPlatformDataEntity entity);
        void showDataError();
    }


    abstract class AbsAccountPresenter extends BasePresenter<AbsAccountView,AbsAccountModel> {
        //使用两个接口 分别获取用户信息
        public abstract void loadUserAccountInfo() ;
    }
}
