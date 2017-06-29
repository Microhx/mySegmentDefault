package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.PageEntity;

import java.util.List;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/23 - 13:54 <p>
 * interface :
 */

public interface CommonContract {

    interface CommonModel<D> extends BaseModel {
        Observable<D> getCommentListDatas(int type, String channel, int startPages) ;
    }

    interface  CommonView<D> extends BaseView {
        void getCommonListDatas(int startPages, List<D> mDataList , PageEntity pageEntity) ;

        void getRequestError(int startPage) ;
    }

    abstract class Presenter extends BasePresenter<CommonView, CommonModel> {
         public abstract void getCommonListDatas(int type ,String channel ,int startPages) ;
    }

}
