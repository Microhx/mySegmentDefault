package com.micro.mysegmentdefault.base.data;

import com.micro.mysegmentdefault.entity.PageEntity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 列表式 请求数据 基本模型 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 18:59 <p>
 * interface :
 */

@Deprecated
public class ListBaseDataEntity<T extends BaseDataInterface> {

    public int status;
    public DataEntityItem data;
    public String message;

    public class DataEntityItem {
        public PageEntity page ;
        public List<T> rows;
    }

}
