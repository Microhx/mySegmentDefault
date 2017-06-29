package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : 标签详情 契约类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 15:51 <p>
 * interface :
 */

public interface TagDetailContract {

    interface DetailView<D> extends BaseView {
        //数据错误
        void onDataError();

        //数据获取成功
        void onDataSuccess(D d);


        //关注标签状态
        void onFollowTagStatus(boolean success);

        //取消关注标签状态
        void onCancelFollowTagStatus(boolean success);

    }

    interface DetailModel<D> extends BaseModel {
        Observable<D> getTagDetailInfo(String tagName);

        Observable<Boolean> followTagStatus(String tagId);

        Observable<Boolean> cancelFollowTagStatus(String tagId);
    }

    abstract class Presenter extends BasePresenter<DetailView,DetailModel> {
        public abstract void getTagDetailInfo(String tagName) ;

        //关注某标签
        public abstract void followTags(String tagId);

        //取消关注某标签
        public abstract void cancelFollowTags(String tagId);


    }

}
