package com.micro.mysegmentdefault.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/31 - 10:26 <p>
 * interface :
 */

public class WebBrowserActivity extends CommonWebActivity {

    private String mUrl ;


    public static void start(Context ctx , String url) {
        Intent _intent = new Intent(ctx,WebBrowserActivity.class);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _intent.putExtra("url",url);
        ctx.startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mUrl = getIntent().getStringExtra("url");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if(null != view && !TextUtils.isEmpty(view.getTitle())) {
            mTagTitle.setText(view.getTitle());
        }
    }


    @Override
    protected void initViews() {
        super.initViews();

        //设置标题
        //mTagTitle.setText(R.string.str_article);

        //加载目标网页
        mWebView.loadUrl(mUrl);
    }
}
