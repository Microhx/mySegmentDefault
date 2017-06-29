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

public class TagDetailArticleEntity {

    public int status;
    public String message;
    public Data data;

    public static class Data {
        public List<Item> rows;
        public PageEntity page;
    }

    public static class Item implements BaseDataInterface {
        public String id;
        public String url;
        public String title;
        public String votes;
        public String bookmarks;
        public String createdDate;
        public String excerpt;
        public String comments;

        public Blog blog ;

        public List<Tag> tags ;

        public User user ;
    }

    public static class Blog {
        public String name ;
        public String url ;
    }

    public static class Tag {
        public String name ;
        public String url ;
        public String id ;
    }

    public static class User {
        public String id ;
        public String name ;
        public String rank ;
        public String url ;
        public String avatarUrl ;
    }


}
