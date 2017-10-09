package com.micro.mysegmentdefault.base.mvp.presenter;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.base.mvp.model.BaseListCacheModel;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.BaseRequestData;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/30 - 11:25 <p>
 * interface :
 */

public class BaseListCachePresenter<V extends BaseRefreshView,
                                    M extends BaseListCacheModel<BaseRequestData<Data>>,
                                    Data extends BaseDataInterface>

                                   extends BaseRefreshPresenter<V,M> {

    public void getCommonListDatas(final int type, final String channel,final int startPages) {

        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<BaseRequestData<Data>>() {

                @Override
                public void call(BaseRequestData<Data> dataBaseRequestData) {
                    if (null != dataBaseRequestData && 0 == dataBaseRequestData.status && null != dataBaseRequestData.data) {
                        List<Data> itemList = dataBaseRequestData.data.rows;
                        PageEntity pageEntity = dataBaseRequestData.data.page;
                        mView.getCommonListDatas(startPages, itemList, pageEntity);

                        //save the cache
                        mModel.saveCache(type,channel,startPages,dataBaseRequestData);
                    }else {
                        mView.getRequestError(startPages);
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    LogUtils.d("---->call throwable---->>>" + throwable);

                    mView.getRequestError(startPages);
                }
            }) ;
    }

}
