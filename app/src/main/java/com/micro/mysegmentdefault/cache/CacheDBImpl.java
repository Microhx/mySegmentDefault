package com.micro.mysegmentdefault.cache;

import android.text.TextUtils;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.CacheBean;
import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.greendao.gen.CacheBeanDao;
import com.micro.mysegmentdefault.greendao.gen.DaoMaster;
import com.micro.mysegmentdefault.greendao.gen.DaoSession;
import com.micro.mysegmentdefault.utils.Constant;
import com.micro.mysegmentdefault.utils.LogUtils;
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
    public CacheStateBean getCacheInfo(String cacheName) {
        CacheStateBean stateBean = new CacheStateBean();

        CacheBeanDao cacheBeanDao = mDaoSession.getCacheBeanDao();
        final CacheBean cacheBean = cacheBeanDao.load(cacheName);
        stateBean.cacheBean = cacheBean;
        stateBean.isNormal = false;

        if(cacheBean != null && !TextUtils.isEmpty(cacheBean.getCacheInfo())) {
            //缓存存在 此时需要判断时间的有效性 判断缓存5m之后过期时间

            LogUtils.d("-----cacheSetting time------" + cacheBean.getAddTime() + "----set time----" + TimeUtils.getCurrentOffsetTime(cacheBean.getAddTime()));

            if(TimeUtils.getCurrentOffsetTime(cacheBean.getAddTime()) < Constant.CACHE_DEAD_TIME) {
                stateBean.isNormal = true ;
            }
        }

        return stateBean;
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

        cacheBeanDao.insertOrReplace(bean);
    }
}
