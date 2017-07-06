package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserBlogDataEntity;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 14:57 <p>
 * interface :
 */

public interface WriteArticleContract {

    interface AbsWriteArticleView extends BaseView {
        void showUserBlogInfo(UserBlogDataEntity dataEntity);

        void showUserCommitBlog(OnlyData data);

        Context getContext();
    }

    interface AbsWriteArticleModel extends BaseModel{
        Observable<UserBlogDataEntity> loadUserBlogInfo(String token);

        Observable<OnlyData>  commitUserBlog(Map<String,String> options);
    }

     abstract class AbsWritePresenter extends BasePresenter<AbsWriteArticleView,AbsWriteArticleModel> {
         public abstract void loadUserBlogInfo();

         public abstract void commitUserBlog(Map<String,String> options);


    }

}
