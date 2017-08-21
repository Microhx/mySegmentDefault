package com.micro.mysegmentdefault.base.mvp.view;

import com.micro.mysegmentdefault.entity.BaseDataEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 14:57 <p>
 * interface :
 */

public interface AbsUserNewsCommentView<D> extends BaseRefreshView<D>{

    //赞数据
    void zanOperationFinish(String type , String number) ;

    //赞失败
    void zanOperationError(BaseDataEntity entity);
}
