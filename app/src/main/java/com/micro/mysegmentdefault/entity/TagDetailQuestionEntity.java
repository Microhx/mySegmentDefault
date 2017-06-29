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

public class TagDetailQuestionEntity {

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
        public String created;
        public String createdDate;
        public String siteId;
        public String excerpt;
        public boolean isAccepted;
        public boolean isBookMarked;
        public String viewsWord;
        public String answers;
        public boolean isClosed;
        public String views;
        public String followers;

        public List<Tag> tags;
    }

    public static class Tag {
        public String name;
        public String url;
        public String id;
    }



}
