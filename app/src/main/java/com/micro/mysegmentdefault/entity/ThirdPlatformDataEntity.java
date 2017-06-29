package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 第三方平台登录信息<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 22:33 <p>
 * interface :
 */

public class ThirdPlatformDataEntity {

    public int status;
    public String message;
    public Data data;

    public static class Data {
        //这里使用已经绑定的数据
        //未绑定的按照null去计算
        public Account bindings;
    }

    public static class Account {
        public Platform qq;
        public Platform weibo;
        public Platform weixin;
        public Platform google;
        public Platform github;
    }



    public static class Platform {
        public String id;
        public String is_hide;
        public String name;
        public String token;
        public String type;
        public String url;
        public String user_id;

    }

}
