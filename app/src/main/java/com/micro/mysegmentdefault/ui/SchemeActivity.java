package com.micro.mysegmentdefault.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.ui.comment.WebBrowserActivity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.SchemeUtils;

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

        String urlPath = uri.getPath();
        String host = uri.getHost();

        if(SchemeUtils.URL.contains(urlPath) && SchemeUtils.URL_SHEME.contains(host)){
            gotoTargetActivity(uri);
        }else {
            WebBrowserActivity.start(SchemeActivity.this,uri.toString());
        }

        /*if(!TextUtils.isEmpty(urlPath) && urlPath.contains("/")) {
            String tag = urlPath.split("/")[1];
            String info = urlPath.split("/")[2];

            LogUtils.d("tag : " + tag + " , info : " + info);

            switch (tag) {
                case "u":  //用户中心
                    UserZoneActivity.start(this,info);
                    break;

                case "a": //文章
                    CommonWebActivity.start(info,ArticleDetailActivity.class);
                   break;

                case "q" : //问答
                    CommonWebActivity.start(info,QuestionDetailActivity.class);
                    break;

                default:
                    WebBrowserActivity.start(this,uri.toString());
            }
        }
*/

        finish();
    }

    private void gotoTargetActivity(Uri uri) {
        LogUtils.d("host : " + uri.getHost());

        LogUtils.d("uri  : " + uri);

        LogUtils.d("path : " + uri.getPath());

    }
}
