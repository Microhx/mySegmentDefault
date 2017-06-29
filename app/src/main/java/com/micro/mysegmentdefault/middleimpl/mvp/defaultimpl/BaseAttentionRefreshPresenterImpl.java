package com.micro.mysegmentdefault.middleimpl.mvp.defaultimpl;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.base.data.ListBaseDataEntity;
import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : 此包下的 都是默认实现 <p>
 *        本实现是 对Presenter的
 *
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 18:47 <p>
 * interface :
 */

@Deprecated
public class BaseAttentionRefreshPresenterImpl<V extends BaseRefreshView,
                                               M extends BaseRefreshModel,
                                               D extends BaseDataInterface>
        extends BaseRefreshPresenter<V,M> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        /*mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<ListBaseDataEntity<D>>() {
            @Override
            public void call(ListBaseDataEntity<D> entity) {
                if(null != entity && entity.status == 0) {
                    mView.getCommonListDatas(startPages,entity.data.rows,entity.data.page);
                }else{
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("BaseAttentionRefreshPresenter error : " + throwable);
                mView.getRequestError(startPages);
            }
        });*/

        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<ListBaseDataEntity<D>>() {
            @Override
            public void call(ListBaseDataEntity entity) {

                LogUtils.d("---onCall--------->>" + entity.data.rows + "---------->>" + entity.data.rows.get(0) + "----->>" + entity.data.rows.get(0).getClass());


                /*if(null != entity && entity.status == 0) {
                    mView.getCommonListDatas(startPages,entity.data.rows,entity.data.page);
                }else{
                    mView.getRequestError(startPages);
                }*/
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("the error is " + throwable);
            }
        }) ;

    }
}
