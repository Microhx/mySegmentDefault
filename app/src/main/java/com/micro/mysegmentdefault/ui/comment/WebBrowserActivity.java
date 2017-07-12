package com.micro.mysegmentdefault.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.SelectorImageButton;

import java.util.HashMap;
import java.util.Map;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/31 - 10:26 <p>
 * interface :
 */

public class WebBrowserActivity extends CommonWebActivity implements View.OnClickListener {

    private String mUrl;

    private SelectorImageButton mUndoButton;
    private SelectorImageButton mRedoButton;
    private SelectorImageButton mRefreshButton;

    public static void start(Context ctx, String url) {
        Intent _intent = new Intent(ctx, WebBrowserActivity.class);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _intent.putExtra("url", url);
        ctx.startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mUrl = getIntent().getStringExtra("url");
    }


    @Override
    protected void initViews() {
        super.initViews();

        //设置底部标签
        settingBottomViews();


        //加载目标网页
        mWebView.loadUrl(mUrl);
    }

    private void settingBottomViews() {
        mUndoButton = new SelectorImageButton(getApplicationContext());
        mUndoButton.setImageResource(R.drawable.ic_undo_black_24dp);
        mUndoButton.setColorStateList(getResources().getColorStateList(R.color.brower_bg_selector));
        mZanLayout.removeAllViews();
        mZanLayout.addView(mUndoButton, getDefaultLayoutParams());

        mRedoButton = new SelectorImageButton(getApplicationContext());
        mRedoButton.setImageResource(R.drawable.ic_redo_black_24dp);
        mRedoButton.setColorStateList(getResources().getColorStateList(R.color.brower_bg_selector));
        mCollectLayout.removeAllViews();
        mCollectLayout.addView(mRedoButton, getDefaultLayoutParams());

        mRefreshButton = new SelectorImageButton(getApplicationContext());
        mRefreshButton.setTintDrawable(R.drawable.ic_browser_refresh,getResources().getColor(R.color.app_theme_color));
        mCommentLayout.removeAllViews();
        mCommentLayout.addView(mRefreshButton, getDefaultLayoutParams());

        mUndoButton.setOnClickListener(this);
        mRedoButton.setOnClickListener(this);
        mRefreshButton.setOnClickListener(this);
    }

    private LinearLayout.LayoutParams mLayoutParams;

    private LinearLayout.LayoutParams getDefaultLayoutParams() {
        if (null == mLayoutParams) {
            mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return mLayoutParams;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }


    @Override
    public void onPageStart() {
        changeImageBottomStatus();
        mRefreshButton.setSelected(false);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (null != view && !TextUtils.isEmpty(view.getTitle())) {
            mTagTitle.setText(view.getTitle());
        }

        changeImageBottomStatus();
        mRefreshButton.setSelected(true);
    }


    private void changeImageBottomStatus() {
        mUndoButton.setEnabled(mWebView.canGoBack());
        mRedoButton.setEnabled(mWebView.canGoForward());
    }


    @Override
    public void onClick(View v) {
        if (v == mUndoButton) {
            mWebView.goBack();
        } else if (v == mRedoButton) {
            mWebView.goForward();
        } else if (v == mRefreshButton) {
            mWebView.reload();
        }
    }
}
