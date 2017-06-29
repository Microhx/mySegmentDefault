package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.UpdatePasswordEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 18:45 <p>
 * interface :
 */

public interface UpdatePasswordContract {

    interface AbsUpdatePasswordView extends BaseView {
        void onUpdatePasswordResult(UpdatePasswordEntity entity);
    }

    interface AbsUpdatePasswordModel extends BaseModel {
        Observable<UpdatePasswordEntity> updatePassword(String oldPassword , String newPassword , String newPasswordAgain , String token);

    }

   abstract class AbsUpdatePasswordPresenter extends BasePresenter<AbsUpdatePasswordView,AbsUpdatePasswordModel> {
        public abstract void updatePassword(String oldPassword, String newPassword , String newPasswordAgain) ;

    }

}
