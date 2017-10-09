package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseListCacheModel;
import com.micro.mysegmentdefault.cache.CacheHelper;
import com.micro.mysegmentdefault.entity.BaseRequestData;
import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.entity.NewCategoryListData;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.Constant;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:24 <p>
 * interface :
 */

public class ArticleModel extends BaseListCacheModel<BaseRequestData<NewCategoryListData>> {

    @Override
    protected Observable<BaseRequestData<NewCategoryListData>> getNetWorkRequest(int type, String channel,
                                                                                 int startPages, CacheStateBean stateBean) {
        if(type == 0) {
            return Api.
                    getApiService(0).
                    getNewsBaseDataEntityList(channel, String.valueOf(startPages)).
                    compose(RxSchedulers.<BaseRequestData<NewCategoryListData>>io_main());

        } else if(type == Integer.MIN_VALUE) {

            return Api.
                    getApiService(0).
                    getUserZoneArticleDataEntity(channel, UserLogic.getUserToken(),startPages).
                    compose(RxSchedulers.<BaseRequestData<NewCategoryListData>>io_main()) ;
        }

        return Api.
                getApiService(0).
                getNewsExtendsDataEntityList(channel, String.valueOf(startPages)).
                compose(RxSchedulers.<BaseRequestData<NewCategoryListData>>io_main()) ;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected BaseRequestData<NewCategoryListData> parseString2Target(String s) {
        try {
            return BaseRequestData.fromJson(s,NewCategoryListData.class);
        }catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }

    @Override
    public void saveCache(int type, String channel, int startPages, BaseRequestData<NewCategoryListData> dataBaseRequestData) {
        if(startPages == Constant.PAGE_STEP&& null != dataBaseRequestData
                && 0 == dataBaseRequestData.status && !dataBaseRequestData.isCache) {

            String cacheInfo = dataBaseRequestData.toJson(NewCategoryListData.class);
            CacheHelper.getInstance().saveCache(type,channel,startPages,cacheInfo);
            LogUtils.d("----finish save cache------>>>");
        }
    }

}
