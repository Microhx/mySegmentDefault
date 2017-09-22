package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 14:02 <p>
 * interface :
 */

public interface ArticleDetailContract {

    interface ArticleDetailView extends BaseView {

        void showArticleDetailEntity(ArticleDetailEntity entity) ;

        //加载数据失败
        void loadDataError();

        void zanOperation(BaseDataEntity entity) ;

        void followUserResult(boolean result , String userId);
    }


    interface ArticleDetailModel extends BaseModel {
        Observable<ArticleDetailEntity> getArticleDetailDataEntity(String newsId) ;

        Observable<BaseDataEntity> zanOperation(boolean isCancel,String newsId,String token);

        Observable<FollowDataEntity> followOrCancelUser(boolean isCancel , String userId);
    }

    abstract class DetailPresenter extends BasePresenter<ArticleDetailView,ArticleDetailModel> {
        public abstract void loadArticleDetailInfo(String newsId) ;
    }



}
