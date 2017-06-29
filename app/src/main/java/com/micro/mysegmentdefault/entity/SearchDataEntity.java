package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户搜索DataEntity <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 14:47 <p>
 * interface :
 */

public class SearchDataEntity {

    public String message;
    public int status;
    public Search data;

    public static class Search {
        public PageEntity page;
        public List<SearchItem> rows;
    }

    public static class SearchItem implements BaseDataInterface{
        public String type;
        public String excerpt;
        public String iconUrl;
        public String id;
        public String name;
        public String url;

        public String answers;
        public String bookmarks;
        public String followers;
        public String isAccepted;

        public String title;

        public List<Tag> tags;

        public User user;

        public String avatarUrl;
        public String rank;
        public String slug;

        public String votes;

    }

    public static class Tag {
        public String id;
        public String name;
        public String url;

    }

    public static class User {
        public String avatarUrl;
        public String id;
        public String name;
        public String url;
    }





}
