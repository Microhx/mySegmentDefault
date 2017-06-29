package com.micro.mysegmentdefault.middle.presenter;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;

import java.util.Map;

/**
 * author : micro_hx <p>
 * desc : 用户收藏夹 presenter抽象类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 18:32 <p>
 * interface :
 */

public abstract class AbsUserAddCollectPresenter<V extends BaseRefreshView,M extends BaseRefreshModel> extends BaseRefreshPresenter<V,M> {

    public abstract void addUserCollectDataEntity(String newsId , int tagType, Map<String,String> collectIds);
}
