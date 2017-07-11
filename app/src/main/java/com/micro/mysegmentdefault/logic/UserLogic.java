package com.micro.mysegmentdefault.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.UserLoginDataEntity;

/**
 * author : micro_hx <p>
 * desc : 用户逻辑 --> 登录检查<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 16:11 <p>
 * interface :
 */

public class UserLogic {

    private static SharedPreferences mSharePreferences;

    /**
     * 检查用户是否登录
     * @return
     */
    public static boolean checkUserLogin() {
        return !TextUtils.isEmpty(getUserToken());
    }

    public static void saveUserLoginInfo(UserLoginDataEntity.DataItem dataItem) {
        if(null == dataItem) return;
        initSp();

        UserLoginDataEntity.User user = dataItem.user;
        mSharePreferences.
                edit().
                putString("u_token" , dataItem.token).
                putString("u_photo",user.avatarUrl).
                putString("u_id",user.id).
                putString("u_name",user.name).
                putString("u_mail",user.mail).
                putString("u_phone",user.phone).
                putString("u_slug",user.slug).  //micro_hx
                putString("u_url" , user.url).  // /u/micro_hx
                apply();

        //LogUtils.d("----getAll----->>" + mSharePreferences.getAll());
    }

    /**
     * 获取用户token值
     * @return
     */
    public static String getUserToken() {
        initSp();
        return mSharePreferences.getString("u_token","");
    }


    public static String getUserSlug() {
        initSp();
        return mSharePreferences.getString("u_slug","");
    }

    public static String getUserUrl(){
        initSp();
        return mSharePreferences.getString("u_url","");
    }

    public static String getUserPhoto(){
        initSp();
        return mSharePreferences.getString("u_photo","");
    }

    public static String getUserName(){
        initSp();
        return mSharePreferences.getString("u_name" , "");
    }


    public static String getUserId(){
        initSp();
        return mSharePreferences.getString("u_id","");
    }

    public static String getUserEmail(){
        initSp();
        return mSharePreferences.getString("u_email" , "");
    }


    private static void initSp(){
        if(null == mSharePreferences) {
            mSharePreferences = SegmentApplication.getApplication().getSharedPreferences("segment.data", Context.MODE_PRIVATE);
        }
    }


    /**
     * 清除所有
     */
    public static void clearAll() {
        initSp();
        mSharePreferences.edit().clear().apply();
    }
}
