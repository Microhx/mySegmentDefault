package com.micro.mysegmentdefault.middle.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.BaseDataEntity;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 23:13 <p>
 * interface :
 */

public interface AbsUserPrivateEventDetailModel<D> extends BaseRefreshModel<D>{

    Observable<BaseDataEntity> onDeleteUserPrivateEvent(String eventId);

}
