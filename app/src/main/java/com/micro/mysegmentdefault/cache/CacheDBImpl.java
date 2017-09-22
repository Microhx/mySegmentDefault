package com.micro.mysegmentdefault.cache;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.CacheBean;
import com.micro.mysegmentdefault.greendao.gen.CacheBeanDao;
import com.micro.mysegmentdefault.greendao.gen.DaoMaster;
import com.micro.mysegmentdefault.greendao.gen.DaoSession;
import com.micro.mysegmentdefault.utils.TimeUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/22 - 11:52 <p>
 * interface :
 */

public class CacheDBImpl implements CacheDB {

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    private static volatile  CacheDBImpl mInstance = null ;

    private CacheDBImpl() {
        if(null == mInstance) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(SegmentApplication.getApplication(),"segment.db");
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static CacheDBImpl getInstance() {
        if(null == mInstance) {
            synchronized (CacheDBImpl.class) {
                if(null == mInstance) {
                    mInstance = new CacheDBImpl();
                }
            }
        }
        return mInstance ;
    }

    @Override
    public boolean cacheExist(String cacheName) {
        CacheBeanDao cacheBeanDao = mDaoSession.getCacheBeanDao();
        return cacheBeanDao.load(cacheName) != null ;
    }

    @Override
    public String getTargetCache(String cacheName) {
        CacheBean bean = mDaoSession.getCacheBeanDao().load(cacheName);
        return null == bean ? "" : bean.getCacheInfo();
    }

    @Override
    public boolean needNewRequest(int type, String channel, int startPage,String cacheName) {
        //TODO
        return true;
    }

    @Override
    public void saveCache(String cacheName, String cacheInfo) {
        CacheBeanDao cacheBeanDao = mDaoSession.getCacheBeanDao();

        CacheBean bean = new CacheBean();
        bean.setAddTime(TimeUtils.getCurrentTime());
        bean.setCacheInfo(cacheInfo);
        bean.setCacheName(cacheName);
        bean.setOther("");

        cacheBeanDao.insert(bean);
    }
}
