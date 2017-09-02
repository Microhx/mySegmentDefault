package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:15 <p>
 * interface :
 */

public class ArticleDataEntity {
    public int status ; //article
    public Data data ;
    public String message ;

    public static class Data {
        public List<Item> rows ;
        public PageEntity page ;
    }

    public static class Item implements BaseDataInterface {
        public String id;
        public String url ;
        public String title ;
        public String votes ;
        public String bookmarks;
        public String createdDate ;
        public String excerpt ;
        public String viewsWord;
        public String comments ;

        public Blog blog ;

        public List<Tag> tags;

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
        public String avatarUrl;
        public String url ;
    }


}
