package com.micro.mysegmentdefault.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 13:13 <p>
 * interface :
 */

public class CustomWebViewClient extends WebViewClient {

    private ProgressBar mProgressbar ;
    private WebViewClientLoadingListener mListener ;


    public CustomWebViewClient(ProgressBar progressbar,WebViewClientLoadingListener mListener) {
        this.mProgressbar = progressbar;
        this.mListener = mListener;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mProgressbar.setVisibility(View.GONE);
        if(null != mListener) mListener.onPageFinished(view,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mProgressbar.setVisibility(View.VISIBLE);
        if(null != mListener) mListener.onPageStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        /*String str = request.getUrl().toString();
        if((str.startsWith("file:///img/") || str.startsWith("file:///image"))) {}*/
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return super.shouldInterceptRequest(view, url);
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtils.d("target url is : " + url);


        Uri localUri = Uri.parse(url);
        Context localContext = view.getContext();
        Intent _intent = new Intent(localContext,SchemeActivity.class);
        _intent.setData(localUri);
        _intent.putExtra("inner",true);
        localContext.startActivity(_intent);

        return true ;
    }


    public interface WebViewClientLoadingListener {
        void onPageStart();
        void onPageFinished(WebView view, String url);
    }


}
