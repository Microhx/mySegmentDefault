package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.ActivityDetailDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/27 - 17:08 <p>
 * interface :
 */

public interface ActivityDetailContract {

    interface ActivityModel extends BaseModel {
        Observable<String> getActivityInfo(String activityId,String token);
    }

    interface ActivityView extends BaseView {
      void showActivityInfo(String webInfo);
    }

    abstract class AbsActivityPresenter extends BasePresenter<ActivityView,ActivityModel>{
        public abstract void getActivityInfo(String activityId);

    }

}
