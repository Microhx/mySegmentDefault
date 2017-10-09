package com.micro.mysegmentdefault.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.entity.NewToutiaoListData;
import com.micro.mysegmentdefault.network.Api;

import java.util.Collection;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 基本工具操作<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/23 - 15:50 <p>
 * interface :
 */

public class CommonUtils {

    public static boolean collectionIsNull(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }


    public static boolean collectionCheckIndex(Collection<?> coll, int index) {
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
     *
     * @param ctx
     * @return
     */
    public static int getScreenWidth(Activity ctx) {
        DisplayMetrics metrics = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    public static int getScreenHeigth(Activity ctx) {
        DisplayMetrics metrics = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }


    public static String getArticleTagList(List<HomeDataEntity.NewsTypes> list) {
        if (collectionIsNull(list)) return "";
        return "#" + list.get(0).name;
    }

    public static String getToutiaoTagTagList(List<NewToutiaoListData.NewsTypes> list) {
        if (collectionIsNull(list)) return "";
        return "#" + list.get(0).name;
    }


    public static String convertList2String(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public static boolean checkDataIsTure(String data) {
        int result = 0;
        try {
            result = Integer.parseInt(data);
        } catch (NumberFormatException e) {
        }

        return result == 1;
    }

    public static int safeParseInt(String str, int defalutValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defalutValue;
        }
    }


    public static void hideKeyboard(Context context, View parentView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) { //只判断是否打开
            imm.hideSoftInputFromWindow(parentView.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    public static boolean safeParseBoolean(String boo) {
        return !TextUtils.isEmpty(boo) && ("False".equals(boo) || "false".equals(boo));
    }

    public static int safeParseInt(String str) {
        return safeParseInt(str, 0);
    }

    /**
     * 获取当前时间 毫秒计时
     *
     * @return
     */
    public static long getCurrentMillisTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前秒数
     *
     * @return
     */
    public static long getCurrentSecondTime() {
        return System.currentTimeMillis() / 1000;
    }


    /**
     * 隐藏部分email地址
     *
     * @param email
     * @return
     */
    public static String getSecretEmail(String email) {
        if (TextUtils.isEmpty(email)) return "";
        int index = email.indexOf("@");
        if (index <= 2) return email;

        return email.substring(0, 2) + "****" + email.substring(index);
    }


    /**
     * 隐藏中间四位手机号码
     *
     * @param phone
     * @return
     */
    public static String getSecretPhone(String phone) {
        if (TextUtils.isEmpty(phone)) return "";
        if (phone.length() < 4) return phone;
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }

    public static boolean objIsNull(Object obj) {
        return obj == null;
    }

    public static boolean objIsNotNull(Object obj) {
        return null != obj;
    }


    static StringBuilder mConvertBuilder = new StringBuilder();

    public static String convertTag2String(List<SearchDataEntity.Tag> tags) {
        mConvertBuilder.setLength(0);
        for (SearchDataEntity.Tag tag : tags) {
            mConvertBuilder.append(tag.name);
            mConvertBuilder.append(" , ");
        }

        return mConvertBuilder.toString().length() > 2 ? mConvertBuilder.substring(0, mConvertBuilder.length() - 2) : mConvertBuilder.toString();
    }

    static SpannableStringBuilder mSpannedBuilder = new SpannableStringBuilder();

    public static CharSequence replaceTargetWordWithRed(String target, String keyWords) {
        mSpannedBuilder.clear();
        mSpannedBuilder.append(target);
        String ss = target.toLowerCase();
        int color = Color.parseColor("#E41B23");
        int index = 0;
        int length = target.length();

        while (index < length && !TextUtils.isEmpty(keyWords)) {
            int temp = ss.indexOf(keyWords.toLowerCase(), index);
            if (temp == -1) break;
            mSpannedBuilder.setSpan(new ForegroundColorSpan(color), temp, temp + keyWords.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = temp + keyWords.length();
        }

        //not toString/ but this builder
        return mSpannedBuilder;
    }


    public static CharSequence replaceTargetWordWithAppThemeColor(String target,String keyWords,String url) {
        mSpannedBuilder.clear();
        mSpannedBuilder.append(target);
        String ss = target.toLowerCase();
        //int color = Color.parseColor("#039A63");

        int index = 0;
        int length = target.length();

        while (index < length && !TextUtils.isEmpty(keyWords)) {
            int temp = ss.indexOf(keyWords.toLowerCase(), index);
            if (temp == -1) break;

            mSpannedBuilder.setSpan(new StyleClickSpan(Api.WEB_URL + url), temp, temp + keyWords.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = temp + keyWords.length();
        }

        return mSpannedBuilder;
    }




}
