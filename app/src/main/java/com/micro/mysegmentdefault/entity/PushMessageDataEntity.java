package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 14:21 <p>
 * interface :
 */

public class PushMessageDataEntity {

    public String message ;
    public int status ;
    public Data data;

    public static class Data {
        public int api_answer;
        public int api_comment ;
        public int api_disturb;
        public int api_follow ;
        public int api_invite;
        public int api_mention;
        public int api_message ;
        public int api_vote;

    }

}
