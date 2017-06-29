package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 密码修改 Entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 18:43 <p>
 * interface :
 */

public class UpdatePasswordEntity {

    public String message ;
    public int status;
    public DataItem data;


    public static class DataItem {
        public String notice;
    }




}
