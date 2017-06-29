package com.micro.mysegmentdefault.base.mvp.view;

import com.micro.mysegmentdefault.entity.PageEntity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 基础刷新View<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 17:09 <p>
 * interface :
 */

public interface BaseRefreshView<D> extends BaseView {

    void getCommonListDatas(int startPages, List<D> mDataList , PageEntity pageEntity) ;

    void getRequestError(int startPage) ;
}


