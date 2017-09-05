package com.micro.mysegmentdefault.middleimpl.mvp.model;

import android.text.TextUtils;

import com.micro.mysegmentdefault.entity.ActivityDetailDataEntity;
import com.micro.mysegmentdefault.middle.ActivityDetailContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rx.Observable;
import rx.functions.Func1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/27 - 19:02 <p>
 * interface :
 */

public class ActivityModel implements ActivityDetailContract.ActivityModel {
    @Override
    public Observable<String> getActivityInfo(String activityId, String token) {
        return Api.getApiService(0).getActivityDetailDataEntity(activityId,token).map(new Func1<ActivityDetailDataEntity, String>() {
            public String call(ActivityDetailDataEntity dataEntity) {
                return getParseText(dataEntity);
            }
        }).compose(RxSchedulers.<String>io_main());
    }


    private String getParseText(ActivityDetailDataEntity  data) {
        if(null == data || null == data.data ) return  "" ;

        StringBuilder sb = new StringBuilder();
        ActivityDetailDataEntity.Data innerData = data.data;


        sb.append("<meta charset='utf-8'>");
        sb.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>");

        sb.append("<img src='"+innerData.bannerUrl + "' width='100%'/>") ;
        sb.append("<div>"+innerData.name+"</div>");
        sb.append("<hr/>");
        sb.append("<div>"+innerData.startDate + "  " + innerData.startWeek+"</div>");
        sb.append("<hr/>");
        sb.append("<div>"+innerData.cityName + "  " + innerData.address +"</div>");
        sb.append("<hr/>");

        Document document = Jsoup.parse(innerData.parsedText);
        //获取图片结果
        Elements elements = document.getElementsByTag("img");
        if(null != elements && !elements.isEmpty()) {
            for(Element e : elements) {
                String src = e.attr("src");
                if(!TextUtils.isEmpty(src) && !src.startsWith("http")){
                    e.attr("src" , Api.WEB_URL + e.attr("src"));
                }

                e.attr("width","100%");
            }
        }

        return sb.toString() + document.html();
    }

}
