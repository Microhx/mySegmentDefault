package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseListCacheModel;
import com.micro.mysegmentdefault.cache.CacheHelper;
import com.micro.mysegmentdefault.entity.BaseRequestData;
import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.entity.NewQuestionListData;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.Constant;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 17:06 <p>
 * interface :
 */

public class TagDetailQuestionModel extends BaseListCacheModel<BaseRequestData<NewQuestionListData>> {

    @Override
    protected Observable<BaseRequestData<NewQuestionListData>> getNetWorkRequest(int type, String channel,
                                                                                 int startPages,
                                                                                 CacheStateBean stateBean) {
        if (type == Integer.MIN_VALUE) {
            return Api.
                    getApiService(0).
                    getTagRecommendEntity(channel, String.valueOf(startPages)).
                    compose(RxSchedulers.<BaseRequestData<NewQuestionListData>>io_main());
        }

        return Api.
                getApiService(0).
                getTagDetailQuestionEntity(channel, String.valueOf(startPages)).
                compose(RxSchedulers.<BaseRequestData<NewQuestionListData>>io_main());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected BaseRequestData<NewQuestionListData> parseString2Target(String s) {
        try {
            return BaseRequestData.fromJson(s,NewQuestionListData.class);
        }catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }


    @Override
    public void saveCache(int type, String channel, int startPages, BaseRequestData<NewQuestionListData> dataBaseRequestData) {
        if(startPages == Constant.PAGE_STEP&& null != dataBaseRequestData
                && 0 == dataBaseRequestData.status && !dataBaseRequestData.isCache) {

            String cacheInfo = dataBaseRequestData.toJson(NewQuestionListData.class);
            CacheHelper.getInstance().saveCache(type,channel,startPages,cacheInfo);
            LogUtils.d("----finish save cache------>>>");
        }
    }
}
