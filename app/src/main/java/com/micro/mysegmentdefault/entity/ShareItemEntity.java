package com.micro.mysegmentdefault.entity;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * author : micro_hx <p>
 * desc : 分享实体<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/22 - 22:55 <p>
 * interface :
 */

public class ShareItemEntity {

    //分享展示的头部
    public String mTitle ;

    //分享展示的drawable
    public Drawable mDrawable ;

    //分享需要展示的Intent
    public Intent mIntent;

    public ShareItemEntity(Drawable mDrawable, Intent mIntent, String mTitle) {
        this.mDrawable = mDrawable;
        this.mIntent = mIntent;
        this.mTitle = mTitle;
    }

    public ShareItemEntity(){

    }
}
