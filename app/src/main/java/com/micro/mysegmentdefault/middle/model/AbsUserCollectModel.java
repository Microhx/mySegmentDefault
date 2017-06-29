package com.micro.mysegmentdefault.middle.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.BaseDataEntity;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 18:26 <p>
 * interface :
 */

public interface AbsUserCollectModel<D> extends BaseRefreshModel<D> {

        Observable<BaseDataEntity> userAddCollectDataEntity(String newsId , int tagType, Map<String,String> collectIds , String token);
}
