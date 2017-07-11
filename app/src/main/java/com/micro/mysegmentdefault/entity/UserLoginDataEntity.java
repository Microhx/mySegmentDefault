package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 用户登录 dataEntity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/10 - 15:58 <p>
 * interface :
 */

public class UserLoginDataEntity {

    public String message;
    public int status ;
    public DataItem data;

    public static class DataItem {
        public User user;
        public String token;
    }

    public static class User {
        public String articles;
        public String avatarUrl;
        public String currentStatus;
        public String id;
        public String mail;
        public String name;
        public String phone;
        public String questions;
        public String rank;
        public String slug;
        public String url;
    }





}
