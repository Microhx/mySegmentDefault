package com.micro.mysegmentdefault.logic;

import com.micro.mysegmentdefault.utils.Constant;

/**
 * author : micro_hx <p>
 * desc : 用户逻辑 --> 登录检查<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 16:11 <p>
 * interface :
 */

public class UserLogic {

    /**
     * 检查用户是否登录
     * @return
     */
    public static boolean checkUserLogin() {

        //TODO 为false时，需要跳转登录

        return true ;
    }


    /**
     * 获取用户token值
     * @return
     */
    public static String getUserToken() {
        return Constant.TOKEN;
    }


    //TODO
    public static String getUserUrl() {
        return "micro_hx";
    }

}
