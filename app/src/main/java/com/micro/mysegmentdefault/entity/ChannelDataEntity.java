package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 16:47 <p>
 * interface :
 */

public class ChannelDataEntity {
    public int status ;
    public String message ;
    public List<Item> data;

    public static class Item implements BaseDataInterface {
        public Item(){}

        public Item(String channelName,String channelCode) {
            this.channelName = channelName;
            this.channelCode = channelCode;
        }

        public String channelName ;
        public String channelCode ;

    }




}
