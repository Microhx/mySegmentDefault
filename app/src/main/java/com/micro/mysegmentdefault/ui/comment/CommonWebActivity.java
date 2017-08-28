package com.micro.mysegmentdefault.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.ui.CustomWebViewClient;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 13:55 <p>
 * interface :
 */

public abstract class CommonWebActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity<T, E> implements CustomWebViewClient.WebViewClientLoadingListener {

    @Bind(R.id.id_webview)
    protected WebView mWebView;

    @Bind(R.id.myProgressBar)
    protected ContentLoadingProgressBar mProgressbar;

    @Bind(R.id.id_tag_title)
    protected TextView mTagTitle;

    @Bind(R.id.id_iv_right)
    protected ImageView mIvRightImage ;

    @Bind(R.id.id_tv_zan)
    protected TextView mTvZan;

    @Bind(R.id.id_tv_collection)
    protected TextView mTvCollect ;

    @Bind(R.id.id_tv_comment)
    protected TextView mTvComment ;

    @Bind(R.id.id_layout_zan)
    protected LinearLayout mZanLayout;

    @Bind(R.id.id_layout_collect)
    protected LinearLayout mCollectLayout;

    @Bind(R.id.id_layout_comment)
    protected LinearLayout mCommentLayout;

    @Bind(R.id.id_layout_bottom)
    protected LinearLayout mBottomLayout;

    public static final String CONTENT_ID = "content_id";

    protected String mNewsId;

    public static void start(String newsId,Class<? extends CommonWebActivity> clazz) {
        Intent _intent = new Intent(SegmentApplication.getApplication(), clazz);
        _intent.putExtra(CONTENT_ID, newsId);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SegmentApplication.getApplication().startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mNewsId = getIntent().getStringExtra(CONTENT_ID);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {

        initHeadLayouts();

        initWebViews();
    }

    protected void initHeadLayouts() {
        mIvRightImage.setVisibility(View.GONE);
    }

    private void initWebViews() {
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        //自动加载图片
        settings.setLoadsImagesAutomatically(true);
        //不支持缩放
        settings.setSupportZoom(false);

        //webview读取设置的viewport 即pc版网页
        settings.setUseWideViewPort(true);
        //适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //可以读取文件
        settings.setAllowFileAccess(true);

        mWebView.setWebViewClient(new CustomWebViewClient(mProgressbar,this));

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressbar.setProgress(newProgress);
            }
        });
    }

    @OnClick(R.id.id_iv_back)
    public void onCallBack(View v) {
        finish();
    }


    //web加载监听事件
    @Override
    public void onPageStart() {

    }

    @Override
    public void onPageFinished(WebView view, String url) {

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri localUri = Uri.parse(url);
        Context localContext = view.getContext();
        Intent _intent = new Intent(localContext,SchemeActivity.class);
        _intent.setData(localUri);
        _intent.putExtra("inner",true);
        localContext.startActivity(_intent);

        return true;
    }
}
