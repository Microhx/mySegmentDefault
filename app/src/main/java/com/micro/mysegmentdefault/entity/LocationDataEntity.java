package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 17:44 <p>
 * interface :
 */

public class LocationDataEntity {

    public int status;
    public List<Item> data;
    public String message;


    public static class Item implements BaseDataInterface{

        public String id;
        public String name;
    }


}
