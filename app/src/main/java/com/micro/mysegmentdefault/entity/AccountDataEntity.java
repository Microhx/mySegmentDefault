package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 账户信息<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 22:32 <p>
 * interface :
 *
 * 备注：接口中含有大量的信息 此时我只需要获取用户的mail和phone即可
 *
 */

public class AccountDataEntity {

    public int status;
    public String message ;
    public Data data;

    public static class Data {
        public String mail;
        public String phone;

    }


}
