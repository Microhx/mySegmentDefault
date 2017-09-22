package com.micro.mysegmentdefault.cache;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/22 - 11:39 <p>
 * interface :
 */

public interface CacheDB {

     boolean cacheExist(String cacheName) ;


     String getTargetCache(String cacheName) ;


     boolean needNewRequest(int type, String channel, int startPage , String cacheName) ;


     void saveCache(String cacheName,String cacheInfo);

}
