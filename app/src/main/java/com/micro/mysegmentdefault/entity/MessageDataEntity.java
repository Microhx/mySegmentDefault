package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 消息实体 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/6 - 15:32 <p>
 * interface :
 */

public class MessageDataEntity  {

    public int status;
    public String message ;
    public Data data;

    public static class Data {
        public List<Item> rows;
        public PageEntity page ;
    }

    public static class Item implements BaseDataInterface {
        public String id ;
        public String excerpt;
        public String url;
        public String sentence;
        public String detail;
        public String viewed ;
        public String currentStatus;
        public String createdDate;
        public String type;
    }

}
