package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 标签实体类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:08 <p>
 * interface :
 */

public class TagDataEntity {

    public int status;
    public Data data;
    public String message;

    public static class Data {
        public List<Item> rows;
        public PageEntity page;
    }

    public static class Item implements BaseDataInterface, java.io.Serializable {

        public String id;
        public String url;
        public String name;
        public String feedsUrl;
        public String shortUrl;
        public String excerpt;
        public String followers;
        public boolean isFollowed;
        public String thumbnailUrl;
        public String type;
    }

}
