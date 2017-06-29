package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 私信内容<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 22:04 <p>
 * interface :
 */

public class UserPrivateEventDetailDataEntity {

    public int status ;
    public String message ;
    public List<DataItem> data;

    public static class DataItem implements BaseDataInterface{
        public String id;
        public String content;
        public String created;
        public String createdDate;
        public String isSystem;
        public String userId ;
    }




}
