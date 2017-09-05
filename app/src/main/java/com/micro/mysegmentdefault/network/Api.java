package com.micro.mysegmentdefault.network;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author : micro_hx <p>
 * desc : 网络请求接口<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 15:42 <p>
 * interface :
 */

public class Api {

    public static final String WEB_URL = "https://segmentfault.com" ;

    public static final String BASE_URL = "https://api.segmentfault.com/" ;

    //网络连接超时
    public static final int CONNECT_TIME_OUT = 8000;
    //读取时间
    public static final int READ_TIME_OUT = 8000;

    public Retrofit mRetrofit;

    public ApiService mApiService;

    private static SparseArray<Api> mApiContainer = new SparseArray<>();

    /*
       1. noCache 不使用缓存，全部走网络

        2. noStore 不使用缓存，也不存储缓存

        3. onlyIfCached 只使用缓存

        4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

        5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

        6. minFresh 设置有效时间，依旧如上

        7. FORCE_NETWORK 只走网络

        8. FORCE_CACHE 只走缓存
    */

    //缓存保存两天
    public static final long CACHE_KEEP_TIME = 2 * 24 * 3600 * 1000;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_KEEP_TIME;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public static String getCacheControl() {
        return NetworkUtils.isNetConnected(SegmentApplication.getApplication()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    private Api(int hostType) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(SegmentApplication.getApplication().getExternalCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 50 * 1000 * 1024);

        //添加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder().addHeader("Content-type", "application/json").build();
                return chain.proceed(build);
            }
        };

        OkHttpClient client = new OkHttpClient().
                newBuilder().
                addInterceptor(interceptor).
                addInterceptor(headerInterceptor).
                addInterceptor(mRewriteCacheControlInterceptor).
                addNetworkInterceptor(mRewriteCacheControlInterceptor).
                readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).
                connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS).
                cache(cache).
                build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        mRetrofit = new Retrofit.
                Builder().
                client(client).
                addConverterFactory(GsonConverterFactory.create(gson)).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                baseUrl(BASE_URL).
                build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static ApiService getApiService(int hostType) {
        Api currentApi = mApiContainer.get(hostType);
        if (null == currentApi) {
            currentApi = new Api(hostType);
            mApiContainer.put(hostType, currentApi);
        }
        return currentApi.mApiService;
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetConnected(SegmentApplication.getApplication())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isNetConnected(SegmentApplication.getApplication())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_KEEP_TIME)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };





}
