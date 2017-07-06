package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户写博客返回的实体类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 14:53 <p>
 * interface :
 */

public class UserBlogDataEntity {

    public int status ;
    public String message ;
    public List<BlogItem> data;


    public static class BlogItem {
        public String articles;
        //available
        public String currentStatus;

        public String description;

        public String id ;

        public String name;

        public String url;
    }

}
