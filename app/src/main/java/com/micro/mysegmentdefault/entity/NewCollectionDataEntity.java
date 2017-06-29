package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 22:59 <p>
 * interface :
 */

public class NewCollectionDataEntity {

    public String message ;
    public int status ;
    public DataItem data;

    public static class DataItem {

        public String id ;
        public String isPrivate ;
        public String name ;

    }

}
