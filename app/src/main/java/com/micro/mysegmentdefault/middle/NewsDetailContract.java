package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/15 - 21:05 <p>
 * interface :
 */

public interface NewsDetailContract {

    interface DetailView extends BaseView {
        void showNewsDataDetailEntity(NewsDetailDataEntity entity) ;

        //加载数据失败
        void loadDataError();

        //赞数据
        void zanOperationFinish(String number) ;

        //赞失败
        void zanOperationError();

        void followUserResult(boolean result , String userId);

    }


    interface DetailModel extends BaseModel {
        Observable<NewsDetailDataEntity> getNewsDataDetailDataEntity(String newsId) ;

        Observable<BaseDataEntity> getZanOperationDataEntity(boolean isCancel , String newsId);

        Observable<FollowDataEntity> followOrCancelUser(boolean isCancel , String userId);

    }

    abstract class DetailPresenter extends BasePresenter<DetailView,DetailModel> {
        public abstract void loadArticleDetailInfo(String newsId) ;

        public abstract void zanOperation(boolean isCancel, String newId) ;

        public abstract void followOrCancelUser(boolean isCancel , String userId);


    }

}
