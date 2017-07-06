package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.CreateSpecialColumnContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 17:02 <p>
 * interface :
 */

public class CreateSpecialColumnPresenter extends CreateSpecialColumnContract.AbsCreateSpecialColumnPresenter{

    @Override
    public void commitUserSpecialColumn(String name, String slug, String description) {
        mModel.commitUserSpecialColumn(name,slug,description, UserLogic.getUserToken()).
                subscribe(new RxSubscriber<OnlyData>(mView.getContext(),"") {
                    @Override
                    public void _onNext(OnlyData data) {
                        mView.showCommitResult(data);
                    }

                    @Override
                    public void _onError(Throwable t) {
                        LogUtils.d("commitUseSpecialColumn error:"+t);
                        mView.showCommitResult(null);
                    }
                });


    }
}
