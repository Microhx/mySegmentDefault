package com.micro.mysegmentdefault.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
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

    static int PLACE_COLOR = Color.parseColor("#e6e6e6");
    static int errorImage ;

    static {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            errorImage = R.drawable.image_not_exist_24dp;
        }else{
            errorImage = R.drawable.error_404;
        }
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

        // for android api lower than 21 , the vector drawable will throw Exception

        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .apply(RequestOptions.centerCropTransform().placeholder(new ColorDrawable(PLACE_COLOR)).error(errorImage).dontAnimate())
                .into(iv);
    }

    public static void showUserCircleImageUrl(String url , ImageView iv) {
        if (TextUtils.isEmpty(url)) {
            iv.setImageDrawable(SegmentApplication.getApplication().getResources().getDrawable(R.drawable.ic_avatar));
            return;
        }

        if(!url.startsWith("https://") && !url.startsWith("http://")) {
            url = Api.BASE_URL + url;
        }

        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .apply(RequestOptions.
                        circleCropTransform().
                        placeholder(R.drawable.ic_avatar).
                        error(R.drawable.ic_avatar).
                        dontAnimate())
                .into(iv);
    }


    public static void showUrlImageFixXY(String url, ImageView iv) {
        if(TextUtils.isEmpty(url)) {
            iv.setImageDrawable(new ColorDrawable(PLACE_COLOR));
            return;
        }

        if(!url.startsWith("https://") && !url.startsWith("http://")) {
            url = Api.BASE_URL + url;
        }

        Glide.with(SegmentApplication.getApplication())
                .load(url)
                .apply(RequestOptions.fitCenterTransform().placeholder(new ColorDrawable(PLACE_COLOR)).error(errorImage).dontAnimate())
                .into(iv);
    }



}
