package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 21:24 <p>
 * interface :
 */

public class ActionDataEntity {

    public int status ;

    public String message ;

    public Data data ;

    public static class Data {
        public List<Item> rows;
    }

    public static class Item implements BaseDataInterface {

        public String id ;
        public String sentence;
        public String detailSentence;
        public String triggerImage ;
        public String url ;
        public String title;
        public String date;
        public String excerpt;
        public String mtime ;
        public String triggerType ;
        public String rootObjectType ;

        public Trigger trigger;

        public Info object ;
    }

    public static class Trigger {
        public String id ;
        public String url ;
        public String name ;
        public String thumbnailUrl ;
    }

    public static class Info {
        public String id ;
        public String title;
        public String url;
        public String status;
    }



}
