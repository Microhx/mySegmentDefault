package com.micro.mysegmentdefault.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.ui.comment.WebBrowserActivity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.SchemeUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;

/**
 * URL跳转中转Activity
 */
public class SchemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        //boolean bool = getIntent().getBooleanExtra("inner", false);

        Uri uri = getIntent().getData();
        LogUtils.d("target uri : " + uri);

        String urlPath = uri.getHost();
        String host = uri.getScheme();

        if(SchemeUtils.URL.contains(urlPath) && SchemeUtils.URL_SHEME.contains(host)){
            gotoTargetActivity(uri);
        }else {
            WebBrowserActivity.start(SchemeActivity.this,uri.toString());
        }

        finish();
    }

    private void gotoTargetActivity(Uri uri) {
        LogUtils.d("uri  : " + uri);

        String path = uri.getPath();
        if(TextUtils.isEmpty(path)) {
            WebBrowserActivity.start(this,uri.toString());
        }else {
            String[] pathArray = path.substring(1).split("/") ;

            String tagName = null ;
            String detailInfo = null ;

            if(pathArray.length >= 2) {
                tagName = pathArray[0];
                detailInfo = pathArray[1];
            }

            LogUtils.d("--------------tagName---------" + tagName + "----" + detailInfo);

            if(!TextUtils.isEmpty(tagName)){
                switch (tagName) {
                    case "u" :
                        UserZoneActivity.start(this,detailInfo);
                        break;

                    case "a" :
                        CommonWebActivity.start(detailInfo,ArticleDetailActivity.class);
                        break;

                    case "q" :
                        CommonWebActivity.start(detailInfo,QuestionDetailActivity.class);
                        break;


                    case "t": //tagActivity  TODO 缺少tagId
                        //UserTagDetailActivity.start(detailInfo,"1111");
                        ToastUtils.showMessage(this,"TODO");
                        break;

                    case "p" :  //新闻详细内容
                        ToutiaoDataDetailActivity.start(detailInfo,ToutiaoDataDetailActivity.class);
                        break;

                    case "blog":
                    default:
                        ToastUtils.showMessage(this,"activity not found !!!");
                        break;
                }

            }else {
                LogUtils.d(" target url is unknown ");
            }
        }

    }
}
