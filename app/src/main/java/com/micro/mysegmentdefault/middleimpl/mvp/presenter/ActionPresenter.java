package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.ActionDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 21:32 <p>
 * interface :
 */

public class ActionPresenter extends BaseRefreshPresenter<BaseRefreshView<ActionDataEntity.Item>,BaseRefreshModel<ActionDataEntity>> {


    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type, channel, startPages).subscribe(new Action1<ActionDataEntity>() {
            @Override
            public void call(ActionDataEntity actionDataEntity) {
                if (null != actionDataEntity) {
                    List<ActionDataEntity.Item> itemList = actionDataEntity.data.rows;
                    PageEntity page = new PageEntity();
                    if (!CommonUtils.collectionIsNull(itemList) && itemList.size() >= 15) {
                        page.current = 0;
                        page.total = Integer.MAX_VALUE;
                    } else {
                        page.current = 0;
                        page.total = 0;
                    }

                    mView.getCommonListDatas(startPages, itemList, page);
                } else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("action request error : " + throwable);
                mView.getRequestError(startPages);
            }
        });
    }
}
