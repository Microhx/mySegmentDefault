package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 10:06 <p>
 * interface :
 */

public class UserPageEntity {

    public int status ;
    public String message ;
    public UserPageItem data ;


    public static class UserPageItem {
        public String id ;
        public String homepage ;
        public String questions;
        public String answers;
        public String articles;
        public String notes;
        public String description;
        public String bookmarkCount ;
        public String newsCount;
        public List<ActiveTag> activeTags ;

    }


}
