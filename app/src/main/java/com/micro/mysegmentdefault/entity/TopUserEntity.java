package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 22:54 <p>
 * interface :
 */

public class TopUserEntity {
    public String message ;
    public int status ;
    public Data data ;


    public static class Data {
        public List<UserItem> dayTopUsers;
        public String month ;
        public String monthLeft ;
        public List<UserItem> monthTopUsers;
        public String quarter ;
        public String quarterLeft;
        public List<UserItem> quarterTopUsers;
        public List<UserItem> topUsers ;
        public String weekLeft ;
        public List<UserItem> weekTopUsers;
        public List<UserItem> yearTopUsers;
        public String year ;
        public String yearLeft ;

    }

    public static class UserItem {
        public String avatarUrl;
        public String id;
        public String incr;
        public String name ;
        public String slug ;
        public String url ;
    }





}
