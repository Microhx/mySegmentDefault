package com.micro.mysegmentdefault.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.micro.mysegmentdefault.entity.HomeDataEntity;

import java.util.Collection;
import java.util.List;

import retrofit2.Retrofit;

/**
 * author : micro_hx <p>
 * desc : 基本工具操作<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/23 - 15:50 <p>
 * interface :
 */

public class CommonUtils {

    public static boolean collectionIsNull(Collection<?> coll) {
        return coll == null || coll.isEmpty() ;
    }


    public static boolean collectionCheckIndex(Collection<?> coll , int index) {
        return index >= 0 && index < coll.size();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取屏幕宽度
     * @param ctx
     * @return
     */
    public static int getScreenWidth(Activity ctx) {
        DisplayMetrics metrics = new DisplayMetrics() ;
        ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    public static int getScreenHeigth(Activity ctx) {
        DisplayMetrics metrics = new DisplayMetrics() ;
        ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }


    public static String getArticleTagList(List<HomeDataEntity.NewsTypes> list){
        if(collectionIsNull(list)) return "" ;
        return "#" + list.get(0).name;
    }

    public static String convertList2String(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public static boolean checkDataIsTure(String data) {
        int result = 0 ;
        try {
            result = Integer.parseInt(data);
        }catch (NumberFormatException e){}

        return result == 1 ;
    }

    public static int safeParseInt(String str , int defalutValue) {
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            return defalutValue;
        }
    }

    public static int safeParseInt(String str) {
        return safeParseInt(str,0);
    }

    /**
     * 获取当前时间 毫秒计时
     * @return
     */
    public static long getCurrentMillisTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前秒数
     * @return
     */
    public static long getCurrentSecondTime() {
        return System.currentTimeMillis() / 1000 ;
    }


    /**
     * 隐藏部分email地址
     * @param email
     * @return
     */
    public static String getSecretEmail(String email) {
        if(TextUtils.isEmpty(email)) return "" ;
        int index = email.indexOf("@");
        if(index <= 2) return email;

        return email.substring(0,2) + "****" + email.substring(index);
    }


    /**
     * 隐藏中间四位手机号码
     * @param phone
     * @return
     */
    public static String getSecretPhone(String phone) {
        if(TextUtils.isEmpty(phone)) return "";
        if(phone.length() < 4) return phone;
        return phone.substring(0,3) + "****" + phone.substring(phone.length()-4);
    }



}
