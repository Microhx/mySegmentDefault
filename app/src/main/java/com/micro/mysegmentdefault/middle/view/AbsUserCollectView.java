package com.micro.mysegmentdefault.middle.view;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:33 <p>
 * interface :
 */

public interface AbsUserCollectView<D> extends BaseRefreshView<D> {

    void updateUserCollectionInfo(UserCollectionDetailDataEntity.Parent parent);


    void deleteUserBookmark(BaseDataEntity entity);

    Context getContext();
}
