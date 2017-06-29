package com.micro.mysegmentdefault.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.middle.MultipleSearchContract;
import com.micro.mysegmentdefault.middleimpl.adapter.MultipleSearchFragmentAdapter;
import com.micro.mysegmentdefault.middleimpl.adapter.MultipleSearchHistoryAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.MultipleSearchModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.MultipleSearchPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static rx.schedulers.Schedulers.test;

public class MultipleSearchActivity extends BaseActivity<MultipleSearchPresenter, MultipleSearchModel>
        implements MultipleSearchContract.AbsMultipleSearchView, BaseRecyclerAdapter.OnLoadingHeaderCallBack {

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

    private MultipleSearchFragmentAdapter mSearchFragmentAdapter;


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_multiple_search;
    }

    @Override
    protected void initViews() {
        mSearchEditText.setHint(R.string.str_search_hint);
        initHistoryAdapter();

        initSearchAdapter();

        TextUi();
    }

    private void TextUi() {
        mHistoryRecyclerView.setVisibility(View.GONE);
        mSearchLayout.setVisibility(View.VISIBLE);

    }

    private void initHistoryAdapter() {
        mHistoryAdapter = new MultipleSearchHistoryAdapter(this, R.layout.multiple_search_history_item_layout, BaseRecyclerAdapter.ONLY_HEADER);
        mHistoryAdapter.setOnLoadingHeaderCallBack(this);

        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
        //load data
        mPresenter.loadUserHistorySearch();
    }


    private void initSearchAdapter() {
        List<String> searchList = Arrays.asList(getResources().getStringArray(R.array.search_type_item));
        mSearchFragmentAdapter = new MultipleSearchFragmentAdapter(searchList,getSupportFragmentManager());

        mViewPager.setAdapter(mSearchFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }


    @Override
    public void showHistorySearchResult(List<String> result) {
        mHistoryAdapter.addAll(result);
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderHolder(ViewGroup parent) {
        return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_search_history_header_layout, parent, false));
    }

    @Override
    public void onBindHeaderHolder(RecyclerView.ViewHolder holder, int position) {

    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDelete;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mTvDelete = (TextView) itemView.findViewById(R.id.id_tv_delete);
        }
    }


}
