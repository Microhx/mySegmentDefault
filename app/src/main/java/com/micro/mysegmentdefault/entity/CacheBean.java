package com.micro.mysegmentdefault.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * author : micro_hx <p>
 * desc : 缓存实体类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/22 - 13:40 <p>
 * interface :
 */

@Entity
public class CacheBean {

    /**
     *  cache_name  cache_info  add_time  other
     */

    @Id
    @Property(nameInDb = "cache_id")
    private String cacheName;

    @Property(nameInDb = "cache_info")
    private String cacheInfo;

    @Property(nameInDb = "add_time")
    private long addTime ;

    @Property(nameInDb = "t_other")
    private String other ;

    @Generated(hash = 655973378)
    public CacheBean(String cacheName, String cacheInfo, long addTime,
            String other) {
        this.cacheName = cacheName;
        this.cacheInfo = cacheInfo;
        this.addTime = addTime;
        this.other = other;
    }

    @Generated(hash = 573552170)
    public CacheBean() {
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getCacheInfo() {
        return cacheInfo;
    }

    public void setCacheInfo(String cacheInfo) {
        this.cacheInfo = cacheInfo;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
