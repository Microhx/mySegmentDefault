package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户动态时间线 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/28 - 14:20 <p>
 * interface :
 */

public class UserTimeLineDataEntity {
    public int status;
    public String message ;
    public Data data;


    public static class Data {
        public User user ;
        public List<DataItem> rows;
        public PageEntity page;
    }

    public static class User {
        public String id;
        public String name;
        public String slug;
        public String url;
        public String avatarUrl;
    }


    public static class DataItem implements BaseDataInterface {
        public String sentence;
        public String url;
        public String rootObjectType;
        public String title;
        public String date;
        public String excerpt;
        public String text;

        public Meta meta;

        public String actionName;

        public String detailSentence;

        public User user;

        public ObjectItem object;
    }

    public static class Meta {
        public String followers;
        public String answers;
        public boolean isAccepted;
        public boolean isFollowed;
        public boolean isLiked;
        public boolean isBookmarked;

        public String votes;
        public String bookmarks;
        public String comments;

    }

    public static class ObjectItem {
        public String id;
        public String title;
        public String url;
        public int status;
        public String readFirstImg;
        public String thumbnailUrl;
        public String description;

        public String avatarUrl;
    }


}
