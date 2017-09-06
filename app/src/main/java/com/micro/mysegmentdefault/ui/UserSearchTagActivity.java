package com.micro.mysegmentdefault.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserSearchTagRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserSearchTagModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserTagPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : 用户搜索标签页面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 23:01 <p>
 * interface :
 */

public class UserSearchTagActivity extends
        BaseRefreshActivity<UserTagPresenter, UserSearchTagModel, TagDataEntity.Item>
        implements View.OnClickListener {

    ImageView mIvBack;
    EditText mEdSearch;

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        View headView = LayoutInflater.from(this).inflate(R.layout.search_head_layout, null);
        mIvBack = (ImageView) headView.findViewById(R.id.id_iv_back);
        mEdSearch = (EditText) headView.findViewById(R.id.id_et_search);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = CommonUtils.dip2px(this, 4);
        params.setMargins(margin, margin, margin, margin);
        mTitleContent.addView(headView, params);

        mIvBack.setOnClickListener(this);
        mEdSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    toSearchTarget(mEdSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void toSearchTarget(String s) {
        if (TextUtils.isEmpty(s)) return;
        mPresenter.getCommonListDatas(getCommonType(), s, PAGE_STEP);
    }

    /**
     * 初次进入不请求数据
     * @return
     */
    @Override
    protected boolean requestDataWhenInitData() {
        return false;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new UserSearchTagRecyclerAdapter(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected String getDefaultChannel() {
        return "java";
    }

    @Override
    protected void afterRequestFinish() {
        CommonUtils.hideKeyboard(this,mEdSearch);
    }
}
