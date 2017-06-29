package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/5 - 15:05 <p>
 * interface :
 */

public class TagUploadOtherDataEntity {

    public String message ;
    public int status ;
    public List<Item> data;

    public static class Item {
        public String department ;
        public String description;
        public String id ;
        public String name;
        public String stateName;
        public String url;
        public String role;
    }

}
