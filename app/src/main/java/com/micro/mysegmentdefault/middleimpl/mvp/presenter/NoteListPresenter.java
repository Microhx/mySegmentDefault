package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:57 <p>
 * interface :
 */

public class NoteListPresenter extends BaseRefreshPresenter<BaseRefreshView<NoteDataEntity.Item>,BaseRefreshModel<NoteDataEntity>> {

    @Override
    public void getCommonListDatas(int type, String channel, final int startPages) {
        mModel.getCommentListDatas(type,channel,startPages).subscribe(new Action1<NoteDataEntity>() {
            @Override
            public void call(NoteDataEntity noteDataEntity) {
                if(null != noteDataEntity) {
                    List<NoteDataEntity.Item> itemList = noteDataEntity.data.rows;
                    PageEntity page = noteDataEntity.data.page;
                    mView.getCommonListDatas(startPages,itemList,page);

                }else {
                    mView.getRequestError(startPages);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("noteListPresenterError:" + throwable);
                mView.getRequestError(startPages);
            }
        }) ;


    }
}
