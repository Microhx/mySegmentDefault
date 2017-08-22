package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 头条基础数据结构<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 17:08 <p>
 * interface :
 */

public class HomeDataEntity {

    public int status ;
    public Data data ;
    public String message ;

    public static class Data {
        public List<Item> rows;
        public PageEntity page ;
    }

    public static class Item implements BaseDataInterface{

        public String id ;
        public String title ;
        public String url ;
        public String description ;
        public String currentStatus;
        public String realViews;
        public String votesWord;
        public String comments;
        public String createdDate ;
        public String isLinked ;
        public String cateType ;
        public String readFirstImg;
        public User user ;
        public String originPath;

        public List<NewsTypes> newsTypes ;
    }

    public static class User {
        public String id ;
        public String name;
        public String url ;
        public String avatarUrl ;
    }

    public static class NewsTypes {
        public String name ;
    }


}
