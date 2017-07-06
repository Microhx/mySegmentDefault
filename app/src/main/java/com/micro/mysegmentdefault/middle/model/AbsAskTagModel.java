package com.micro.mysegmentdefault.middle.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.OnlyData;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/1 - 15:55 <p>
 * interface :
 */

public interface AbsAskTagModel<D> extends BaseRefreshModel<D> {

    Observable<OnlyData> addNewUserTag(String tagName,String tagDesc,String token);

}
