package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/10/9 - 15:10 <p>
 * interface :
 */

public class NewQuestionListData implements BaseDataInterface {

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
    public String bookmarks;

    public List<Tag> tags;

    public Question question;

    public static class Tag {
        public String name;
        public String url;
        public String id;
    }

    public static class Question {
        public String id;
        public boolean isAccepted;
        public String answers;
        public String followers;
        public String bookmarks;
    }


}
