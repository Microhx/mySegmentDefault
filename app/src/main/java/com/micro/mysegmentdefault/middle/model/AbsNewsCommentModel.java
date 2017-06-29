package com.micro.mysegmentdefault.middle.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.BaseDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 14:54 <p>
 * interface :
 */

public interface AbsNewsCommentModel<D> extends BaseRefreshModel<D> {

    Observable<BaseDataEntity> getZanOperationDataEntity(boolean isCancel , String newsId);
}
