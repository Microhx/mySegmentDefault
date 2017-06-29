package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 22:50 <p>
 * interface :
 */

public class DiscoverDataEntity {

    public int status ;
    public String message ;
    public Data data;

    public static class Data {
        public List<DiscoverItem> rows;
        public PageEntity page ;
    }

    public static class DiscoverItem {
        public String bannerUrl ;

        public String id ;

        public String url ;
    }

}
