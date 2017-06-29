package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 关注用户 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/15 - 18:34 <p>
 * interface :
 */

public class UserAttentionTagDataEntity {

    public int status;
    public List<AttentionTagItem> data;
    public String message;

    public static class AttentionTagItem implements BaseDataInterface{
        public String id;
        public String url;
        public String name;
        public String feedsUrl;
        public String editUrl;
        public String shortUrl;
        public String excerpt;
        public String followers;
        public String isFollowed;
        public String thumbnailUrl;

    }







}
