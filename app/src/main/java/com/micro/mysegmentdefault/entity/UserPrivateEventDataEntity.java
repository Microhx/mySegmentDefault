package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户私信 entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 17:58 <p>
 * interface :
 */

public class UserPrivateEventDataEntity {

    public int status ;
    public String message ;
    public Data data;


    public static class Data {
        public PageEntity page;

        public List<DataItem> rows;
    }


    public static class DataItem implements BaseDataInterface {
        public String createdDate ;
        public String currentStatus;
        public String eventType ;
        public String id ;
        public LastMessage lastMessage;
        public String sentence;

        public String viewed;
        public String url ;

        public List<TriggerItem> triggerUser ;
    }

    public static class LastMessage {
        public String createdDate ;
        public Content content;

    }

    public static class Content {
        public String content;
    }

    public static class TriggerItem {
        public String avatarUrl;
        public String name;
        public String url;
    }

}
