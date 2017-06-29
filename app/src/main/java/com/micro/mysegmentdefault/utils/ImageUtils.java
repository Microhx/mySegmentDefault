package com.micro.mysegmentdefault.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.network.Api;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 22:24 <p>
 * interface :
 */

public class ImageUtils {


    public static void showLocalUserImage(String url , ImageView iv) {
        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .centerCrop()//裁剪
                .crossFade(0)
                .priority(Priority.LOW)
                .placeholder(new ColorDrawable(Color.parseColor("#e6e6e6")))
                .error(R.drawable.ic_avatar)
                .dontAnimate()
                .into(iv);
    }

    //https://segmentfault.com/img/bVMGY5?w=900&h=500
    public static void showUrlImage(String url, ImageView iv) {
        if(TextUtils.isEmpty(url)) {
            iv.setImageDrawable(SegmentApplication.getApplication().getResources().getDrawable(R.drawable.ic_doc));
           return;
        }

        if(!url.startsWith("https://") && !url.startsWith("http://")) {
            url = Api.BASE_URL + url;
        }

        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .centerCrop()//裁剪
                .crossFade(0)
                .priority(Priority.LOW)
                .placeholder(new ColorDrawable(Color.parseColor("#e6e6e6")))
                .error(R.drawable.image_not_exist_24dp)
                .dontAnimate()
                .into(iv);
    }


    public static void showUrlImageFixXY(String url, ImageView iv) {
        if(TextUtils.isEmpty(url)) {
            iv.setImageDrawable(new ColorDrawable(Color.parseColor("#e6e6e6")));
            return;
        }

        if(!url.startsWith("https://") && !url.startsWith("http://")) {
            url = Api.BASE_URL + url;
        }

        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .crossFade(0)
                .priority(Priority.LOW)
                .placeholder(new ColorDrawable(Color.parseColor("#e6e6e6")))
                .error(new ColorDrawable(Color.parseColor("#e6e6e6")))
                .dontAnimate()
                .into(iv);
    }



}
