package com.micro.mysegmentdefault.utils;

import java.util.Arrays;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 针对不同的网址 先做出过滤 如果是本网站的数据 则进入自己的逻辑 否则进入自定义浏览器逻辑<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/11 - 16:18 <p>
 * interface :
 */

public class SchemeUtils {

    //目前所规定的网站地址
    public static final List<String> URL = Arrays.asList("segmentfault.com", "sf.gg", "www.segmentfault.com", "", null);

    //目前所支持的网站
    public static final List<String> URL_SHEME = Arrays.asList( "http", "https", "file", "sfclient", null);


}
