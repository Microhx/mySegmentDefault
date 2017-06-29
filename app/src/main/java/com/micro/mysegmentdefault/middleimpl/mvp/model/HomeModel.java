package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.middle.CommonContract;
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

public class HomeModel implements BaseRefreshModel<HomeDataEntity> {

    @Override
    public Observable<HomeDataEntity> getCommentListDatas(int type, String channel, int startPages) {
        LogUtils.d("--------channel------------->>" + channel);

        String[] info = channel.split("___");
        String path = CommonUtils.safeParseInt(info[0]) == 0 ? "rank" : "newest";

        if(info.length <= 1) {
            channel = "";
        }else{
            channel = info[1];
        }

        return Api.
                getApiService(0).
                getHomeDataEntityList(path,Constant.TOKEN,channel,startPages).
                compose(RxSchedulers.<HomeDataEntity>io_main());
    }
}
