package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.SocialAccountBindDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 22:15 <p>
 * interface :
 */

public interface SocialAccountShowContract {

    interface AbsSocialAccountView extends BaseView {
        void showSocialAccountResult(SocialAccountBindDataEntity entity);

        void updateSocialAccountInfo(boolean result);
    }

    interface AbsSocialAccountModel extends BaseModel{
        Observable<SocialAccountBindDataEntity> loadSocialAccountInfo(String token);

        Observable<OnlyData> updateSocialAccountInfo(String type, boolean isClose, String token);
    }

   abstract class AbsSocialAccountPresenter extends BasePresenter<AbsSocialAccountView,AbsSocialAccountModel> {

       public abstract void loadSocialAccountInfo();

       public abstract void updateSocialAccountInfo(String type , boolean isClose);
    }
}
