package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 19:24 <p>
 * interface :
 */

public class UserCollectionDetailDataEntity {

    public int status;
    public String message ;
    public Data data;

    public static class Data {
        public Parent parent ;
        public PageEntity page ;
        public List<Item> rows;
    }

    public static class Parent {
        public String id ;
        public String title ;
        public String description;
        public String isPrivate;

        public String num ;
        public String url;
        private boolean isAuthor;
        public User user ;
    }

    public static class User {
        public String  id ;
        public String name;
        public String avatarUrl;
        public String url;
    }

    public static class Item implements BaseDataInterface{
        public String id;
        public String url;
        public String title;
        public String createdDate;
        public String bookmarks;
        public String viewsWord;
        public User user ;
    }



}
