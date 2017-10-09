package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseListCacheModel;
import com.micro.mysegmentdefault.cache.CacheHelper;
import com.micro.mysegmentdefault.entity.BaseRequestData;
import com.micro.mysegmentdefault.entity.CacheStateBean;
import com.micro.mysegmentdefault.entity.NewToutiaoListData;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.Constant;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 17:32 <p>
 * interface :
 */

public class HomeModel extends BaseListCacheModel<BaseRequestData<NewToutiaoListData>> {

    /**
     * 获取网络数据
     * @param type
     * @param channel
     * @param startPages
     * @param stateBean
     * @return
     */
    @Override
    protected Observable<BaseRequestData<NewToutiaoListData>> getNetWorkRequest(int type,
                                                                                String channel,
                                                                                int startPages,
                                                                                CacheStateBean stateBean) {

        if(type >= 0) {
            String[] info = channel.split("___");
            String path = CommonUtils.safeParseInt(info[0]) == 0 ? "rank" : "newest";

            String realChannel;
            if (info.length <= 1) {
                realChannel = "";
            } else {
                realChannel = info[1];
            }

            return Api.
                   getApiService(0).
                   getHomeDataEntityList(path, UserLogic.getUserToken(),realChannel,startPages);
        }

        return Api.
                getApiService(0).
                getUserZoneShareDataEntity(channel,UserLogic.getUserToken(),startPages).
                compose(RxSchedulers.<BaseRequestData<NewToutiaoListData>>io_main());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected BaseRequestData<NewToutiaoListData> parseString2Target(String s) {
        try {
            return BaseRequestData.fromJson(s,NewToutiaoListData.class);
        }catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }

    @Override
    public void saveCache(int type, String channel, int startPages, BaseRequestData<NewToutiaoListData> dataBaseRequestData) {
        if(startPages == Constant.PAGE_STEP&& null != dataBaseRequestData
                && 0 == dataBaseRequestData.status && !dataBaseRequestData.isCache) {

            String cacheInfo = dataBaseRequestData.toJson(NewToutiaoListData.class);
            CacheHelper.getInstance().saveCache(type,channel,startPages,cacheInfo);
            LogUtils.d("----finish save cache------>>>");
        }
    }

}
