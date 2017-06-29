package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 笔记list列表<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:52 <p>
 * interface :
 */

public class NoteDataEntity {

    public int stauts;
    public String message;
    public Data data ;

    public static class Data {
        public List<Item> rows;
        public PageEntity page;
    }

    public static class Item implements BaseDataInterface{
        public String id;
        public String title;
        public String originalAbstract;
        public String language;
        public String createdDate;
        public String forks;
        public String url;
        public String bookmarks;
        public String isPrivate;
        public User user;
    }

    public static class User {
        public String name;
        public String avatarUrl;
        public String url;
    }


}
