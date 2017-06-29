package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 15:25 <p>
 * interface :
 */

public class SearchSubPresenter extends BaseRefreshPresenter<BaseRefreshView<SearchDataEntity.SearchItem>,BaseRefreshModel<SearchDataEntity>>  {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<SearchDataEntity>() {
            @Override
            public void call(SearchDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.getCommonListDatas(startPages,entity.data.rows,entity.data.page);
                }else{
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("searchSubPresenter get data error:"+throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
