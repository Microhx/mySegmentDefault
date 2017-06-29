package com.micro.mysegmentdefault.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.middle.MultipleSearchContract;
import com.micro.mysegmentdefault.middleimpl.adapter.MultipleSearchHistoryAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.MultipleSearchModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.MultipleSearchPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MultipleSearchActivity extends BaseActivity<MultipleSearchPresenter,MultipleSearchModel> implements MultipleSearchContract.AbsMultipleSearchView {

    @Bind(R.id.id_et_search)
    EditText mSearchEditText;

    @Bind(R.id.id_recycler_view)
    RecyclerView mHistoryRecyclerView;

    @Bind(R.id.id_search_layout)
    LinearLayout mSearchLayout;

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    private MultipleSearchHistoryAdapter mHistoryAdapter;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_multiple_search ;
    }

    @Override
    protected void initViews() {
        mSearchEditText.setHint(R.string.str_search_hint);
        initHistoryAdapter();

    }

    private void initHistoryAdapter() {
        //TODO
        mHistoryAdapter = new MultipleSearchHistoryAdapter(this,R.layout.multiple_search_history_item_layout, BaseRecyclerAdapter.ONLY_FOOTER);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
        //load data
        mPresenter.loadUserHistorySearch();
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }


    @Override
    public void showHistorySearchResult(List<String> result) {
        mHistoryAdapter.addAll(result);
    }
}
