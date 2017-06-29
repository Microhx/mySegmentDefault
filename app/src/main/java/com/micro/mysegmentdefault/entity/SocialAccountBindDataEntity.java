package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 用户绑定 社交账号 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 22:13 <p>
 * interface :
 */

public class SocialAccountBindDataEntity {

    public String message;
    public int status;
    public DataItem data ;

    public static class DataItem {
        public boolean douban;
        public boolean fackbook;
        public boolean github;
        public boolean google;
        public boolean linkedin ;
        public boolean qq;
        public boolean twitter;
        public boolean weibo;
        public boolean weixin;
    }



}
