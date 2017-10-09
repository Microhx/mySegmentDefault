package com.micro.mysegmentdefault.base.mvp.model;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : 基础刷新model<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 17:08 <p>
 * interface :
 */

public interface BaseRefreshModel<D> extends BaseModel {

    Observable<D> getCommentListDatas(int type, String channel, int startPages) ;

}
