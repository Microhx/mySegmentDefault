package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.entity.HomeDataEntity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/10/9 - 11:13 <p>
 * interface :
 */

public class NewToutiaoListData implements BaseDataInterface{

    public String id;
    public String title;
    public String url;
    public String description;
    public String currentStatus;
    public String realViews;
    public String votesWord;
    public String comments;
    public String createdDate;
    public String isLinked;
    public String cateType;
    public String readFirstImg;
    public HomeDataEntity.User user;
    public String originPath;

    public List<NewsTypes> newsTypes;

    public static class User {
        public String id;
        public String name;
        public String url;
        public String avatarUrl;
    }

    public static class NewsTypes {
        public String name;
    }

}

