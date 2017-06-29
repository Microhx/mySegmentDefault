package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 标签详情中问题 entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:56 <p>
 * interface :
 */

public class TagDetailUserEntity {

    public int status;
    public String message;
    public Data data;

    public static class Data {
        public List<Item> top;
        public PageEntity page;
    }

    public static class Item implements BaseDataInterface {
        public String id;
        public String url ;
        public String name ;
        public String avatarUrl;
        public String incr ;
        public String slug;
    }

}
