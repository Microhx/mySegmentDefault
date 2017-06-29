package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 用于关注 作者 javaBean<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/31 - 11:20 <p>
 * interface :
 */

public class FollowDataEntity {

    public int status;
    public String message ;
    public Data data;

    public static class Data {
        public String followersWord;
        public String id;
        public boolean isEachFollowed;
        public String name;

    }

}
