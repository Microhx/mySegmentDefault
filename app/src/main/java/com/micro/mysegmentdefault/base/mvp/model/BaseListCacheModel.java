package com.micro.mysegmentdefault.base.mvp.model;

import com.micro.mysegmentdefault.cache.CacheHelper;
import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * author : micro_hx <p>
 * desc : 带有cacheModel<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/30 - 11:07 <p>
 * interface :
 */

public abstract class BaseListCacheModel<T> implements BaseRefreshModel<T>{

    @Override
    public Observable<T> getCommentListDatas(int type, String channel, int startPages) {
        return loadData(type,channel,startPages);
    }

    protected Observable<T> loadData(int type, String channel, int startPages) {

        CacheStateBean stateBean = CacheHelper.getInstance().getCacheInfo(type,channel,startPages);

        if(stateBean.isNormal) { //缓存有效 此时直接加载缓存
            if(null != stateBean.cacheBean) { //此时只加载缓存

                LogUtils.d("just load the cache");

                return parseDataFormCache(type,channel,startPages,stateBean);
            }

            //此时加载网络数据
            LogUtils.d("just load the network cache");

            return getNetWorkRequest(type,channel,startPages,stateBean);

        }else {
            if(null != stateBean.cacheBean) { //缓存存在 此时加载缓存 + 网络请求数据

                LogUtils.d("load the local cache and the network cache");

                //load the local data and the network data at the same time
                //which is faster for this local and the network,not absolutely
                return Observable.merge(
                        parseDataFormCache(type,channel,startPages,stateBean),
                        getNetWorkRequest(type,channel,startPages,stateBean)).
                        compose(RxSchedulers.<T>io_main());
            }

            //此时直接加载网络数据
            LogUtils.d("load the network cache since the local cache is no used!!!");

            return getNetWorkRequest(type,channel,startPages,stateBean);
        }
    }

    /**
     * load the cache data
     * @param type
     * @param channel
     * @param startPages
     * @param stateBean
     * @return
     */
    protected abstract Observable<T> getNetWorkRequest(int type, String channel, int startPages, CacheStateBean stateBean);


    /**
     * load the network data
     * @param type
     * @param channel
     * @param startPages
     * @param stateBean
     * @return
     */
    protected Observable<T> parseDataFormCache(final int type, final String channel, final int startPages, final CacheStateBean stateBean) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String cache = CacheHelper.getInstance().getTargetCache(type,channel,startPages);
                subscriber.onNext(cache);
            }

        }) .map(new Func1<String, T>() {
            @Override
            public T call(String s) {
                T t = parseString2Target(s);
                if(t == null) {
                    stateBean.isNormal = false;
                }else{
                    stateBean.isNormal = true;
                }
                return t ;
            }
        }).compose(RxSchedulers.<T>io_main());
    }

    protected abstract T parseString2Target(String s);


    /**
     * 保存缓存
     * @param type
     * @param channel
     * @param startPages
     * @param dataBaseRequestData
     */
    public abstract void saveCache(int type,String channel,int  startPages, T dataBaseRequestData);

}
