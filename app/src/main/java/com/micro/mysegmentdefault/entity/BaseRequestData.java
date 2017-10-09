package com.micro.mysegmentdefault.entity;

import com.google.gson.Gson;
import com.micro.mysegmentdefault.utils.ClassUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 请求<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/30 - 11:31 <p>
 * interface :
 */

public class BaseRequestData<T> {

    //状态值
    public int status ;

    //目标值
    public Data<T> data ;

    public String message ;

    //是否为缓存
    public boolean isCache ;

    public static class Data<T> {
        public List<T> rows ;
        public PageEntity page ;
    }


    public static BaseRequestData fromJson(String json,Class clazz){
        Gson gson = new Gson();
        Type objectType = ClassUtils.type(BaseRequestData.class,clazz);
        BaseRequestData target =  gson.fromJson(json,objectType);
        target.isCache = true ;

        return target;
    }

    public String toJson(Class<T> clazz){
        Gson gson = new Gson();
        Type objectType = ClassUtils.type(BaseRequestData.class,clazz);
        return gson.toJson(this,objectType);
    }


}
