package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.UserPageEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 10:11 <p>
 * interface :
 */

public interface UserPagerContract {

    interface AbsUserPagerModel extends BaseModel {
        Observable<UserPageEntity> loadUserPagerInfo(String username);
    }

    interface AbsUserPagerView extends BaseView {
        void loadUserPagerInfo(UserPageEntity.UserPageItem item);
        void loadUserPagerError();
    }

    abstract class AbsPresenter extends BasePresenter<AbsUserPagerView,AbsUserPagerModel> {
        public abstract void loadUserPagersInfo(String username) ;
    }
}
