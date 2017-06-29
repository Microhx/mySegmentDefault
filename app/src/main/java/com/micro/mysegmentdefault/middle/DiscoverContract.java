package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.DiscoverDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 23:10 <p>
 * interface :
 */

public interface DiscoverContract {

    interface DiscoverView extends BaseView {
        void showDiscoverData(DiscoverDataEntity dataEntity);

        void onErrorWhenReceiveDiscoverData();

    }

    interface DiscoverModel extends BaseModel {
        Observable<DiscoverDataEntity> getDiscoverDataEntity();
    }

    abstract class Presenter extends BasePresenter<DiscoverView,DiscoverModel> {
        //获取活动推荐
        public abstract void getRecommendActivityList() ;
    }
}
