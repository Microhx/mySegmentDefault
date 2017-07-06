package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.OnlyData;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 16:58 <p>
 * interface :
 */

public interface CreateSpecialColumnContract  {

    interface AbsCreateSpecialColumnModel extends BaseModel{
        Observable<OnlyData> commitUserSpecialColumn(String name,String slug,String description,String token);
    }

    interface  AbsCreateSpecialColumnView extends BaseView{
        void showCommitResult(OnlyData data);

        Context getContext();
    }

    abstract class AbsCreateSpecialColumnPresenter extends BasePresenter<AbsCreateSpecialColumnView,AbsCreateSpecialColumnModel> {
        public abstract void commitUserSpecialColumn(String name,String slug , String description);
    }


}
