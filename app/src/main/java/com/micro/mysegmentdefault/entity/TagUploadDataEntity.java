package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/5 - 15:05 <p>
 * interface :
 */

public class TagUploadDataEntity {

    public String message ;
    public int status ;
    public Data data ;


    public static class Data {
        public List<BestTag> tags;
    }

}
