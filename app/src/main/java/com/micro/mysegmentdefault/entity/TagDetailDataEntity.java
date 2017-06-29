package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 标签详情 entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 15:59 <p>
 * interface :
 */

public class TagDetailDataEntity {

    public int status;
    public String message;
    public Data data;

    public static class Data {
        public String id;
        public String url;
        public String name;
        public String feedsUrl;
        public String editUrl;
        public String shortUrl;
        public String parsedText;
        public String originalText;
        public String excerpt;
        public String followers;
        public boolean isFollowed;
        public String thumbnailUrl;
    }

}
