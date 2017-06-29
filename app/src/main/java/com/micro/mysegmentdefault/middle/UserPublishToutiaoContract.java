package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 19:28 <p>
 * interface :
 */

public interface UserPublishToutiaoContract {

    interface AbsUserPublishToutiaoView extends BaseView {
        void updateToutiaoResult(OnlyData result);

        Context getContext();
    }

    interface AbsUserPublishToutiaoModel extends BaseModel {
        Observable<OnlyData> updateToutiao(String url, String title, String type, String description, String token);
    }

    abstract class AbsUserPublishToutiaoPresenter extends BasePresenter<AbsUserPublishToutiaoView, AbsUserPublishToutiaoModel> {
        public abstract void updateToutiao(String url, String title, String type, String description);
    }

}
