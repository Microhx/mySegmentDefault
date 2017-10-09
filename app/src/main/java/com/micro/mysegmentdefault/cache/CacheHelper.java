package com.micro.mysegmentdefault.cache;

import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.utils.MD5Utils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/22 - 11:33 <p>
 * interface :
 */

public class CacheHelper {

    private static CacheHelper helper ;

    private static CacheDB cacheDB = CacheDBImpl.getInstance();

    public synchronized static CacheHelper getInstance() {
        if(null == helper) {
            helper = new CacheHelper();
        }

        return helper;
    }

    /**
     * 判断缓存是否存在 是否过期 是否为今天第一次请求
     * @param type
     * @param channel
     * @param startPage
     * @return
     */
    public CacheStateBean getCacheInfo(int type, String channel, int startPage) {
        return cacheDB.getCacheInfo(keepCacheName(type,channel,startPage));
    }


    public String getTargetCache(int type, String channel, int startPage) {
        return cacheDB.getTargetCache(keepCacheName(type,channel,startPage));
    }

    public  boolean needNewRequest(int type, String channel, int startPage) {
        return cacheDB.needNewRequest(type,channel,startPage,keepCacheName(type,channel,startPage));
    }

    /**
     * 保存缓存
     *
     * @param type
     * @param channel
     * @param startPage
     * @param cacheInfo
     */
    public void saveCache(int type,String channel,int startPage,String cacheInfo) {
        cacheDB.saveCache(keepCacheName(type,channel,startPage), cacheInfo);
    }

    public static String keepCacheName(int type,String channel,int startPage) {
        return MD5Utils.md5(type + "_" + channel +"__" + startPage);
    }





}
