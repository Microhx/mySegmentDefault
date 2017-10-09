package com.micro.mysegmentdefault.utils;

/**
 * author : micro_hx <p>
 * desc : 时间操作对象<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/22 - 14:01 <p>
 * interface :
 */

public class TimeUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static long getCurrentTime(){

        return System.currentTimeMillis();
    }


    /**
     * 获取差值时间
     * @param setTime
     * @return
     */
    public static long getCurrentOffsetTime(long setTime) {
        return System.currentTimeMillis() - setTime ;
    }




}
