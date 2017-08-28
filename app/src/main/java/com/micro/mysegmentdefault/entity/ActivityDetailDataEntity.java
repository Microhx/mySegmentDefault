package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 活动详情entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:44 <p>
 * interface :
 */

public class ActivityDetailDataEntity {

    public int status;
    public String message;
    public Data data;


    public static class Data {

        public String id;
        public String url;
        public String name;
        public String slug;
        public String cityName;
        public String address;
        public String startDate;
        public String startWeek;
        public String parsedText;
        public String bigBannerUrl;
        public String bannerUrl;

    }


}
