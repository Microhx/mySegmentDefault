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

public class UserAttentionPersonDataEntity {

    public int status;
    public DataEntityItem data;
    public String message;

    public class DataEntityItem {
        public PageEntity page ;
        public List<AttentionPersonItem> rows;
    }


    public static class AttentionPersonItem implements BaseDataInterface{
        public String name ;
        public String slug ;
        public String id;
        public String url;
        public String rank;
        public String avatarUrl;
        public String likedVotes;
        public String followedUsers;
        public String isEachFollowed;



    }







}
