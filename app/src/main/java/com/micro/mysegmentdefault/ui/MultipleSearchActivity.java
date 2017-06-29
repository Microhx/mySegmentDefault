package com.micro.mysegmentdefault.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.micro.mysegmentdefault.middleimpl.subfragment.SearchSubFragment;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.SearchKeyWordsUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

public class MultipleSearchActivity extends BaseActivity<MultipleSearchPresenter, MultipleSearchModel>
        implements MultipleSearchContract.AbsMultipleSearchView,
        BaseRecyclerAdapter.OnLoadingHeaderCallBack, MultipleSearchHistoryAdapter.OnItemSelectListener {

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

    //保存用户临时搜索的词汇
    private Set<String> mTempSearchWords = new HashSet<>();


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

        initEditText();

        initHistoryAdapter();

        initSearchAdapter();
    }

    private void initEditText() {
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String tempWord = mSearchEditText.getText().toString();
                    if (!TextUtils.isEmpty(tempWord)) {

                        mTempSearchWords.add(tempWord);
                        startToSearch(tempWord);
                    }

                    return true;
                }
                return false;
            }
        });
    }


    private void initHistoryAdapter() {
        mHistoryAdapter = new MultipleSearchHistoryAdapter(this, R.layout.multiple_search_history_item_layout, BaseRecyclerAdapter.ONLY_HEADER);
        mHistoryAdapter.setOnLoadingHeaderCallBack(this);
        mHistoryAdapter.setOnItemSelectListener(this);

        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
        //load data
        mPresenter.loadUserHistorySearch();
    }


    private void initSearchAdapter() {
        List<String> searchList = Arrays.asList(getResources().getStringArray(R.array.search_type_item));
        mSearchFragmentAdapter = new MultipleSearchFragmentAdapter(searchList, getSupportFragmentManager());

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
        //保存临时数据
        mTempSearchWords.addAll(result);
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderHolder(ViewGroup parent) {
        return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_search_history_header_layout, parent, false));
    }

    @Override
    public void onBindHeaderHolder(RecyclerView.ViewHolder holder, int position) {
        HeadViewHolder header = (HeadViewHolder) holder;
        header.mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryAdapter.clear();
                //清除缓存
                SearchKeyWordsUtils.clearAllSearchHistory(MultipleSearchActivity.this);
                //临时文件也需要清除
                mTempSearchWords.clear();
            }
        });
    }

    /**
     * 当点击item时
     *
     * @param item
     */
    @Override
    public void onItemSelect(String item) {
        if(TextUtils.isEmpty(item)) return;

        mSearchEditText.setText(item);
        mSearchEditText.setSelection(item.length());

        //开始搜索
        startToSearch(item);
    }

    private void startToSearch(String keyWords) {
        if (View.INVISIBLE != mHistoryRecyclerView.getVisibility()) {
            mHistoryRecyclerView.setVisibility(View.GONE);
            mSearchLayout.setVisibility(View.VISIBLE);
        }

        //设置关键字
        SearchSubFragment.mKeyWords = keyWords;

        //获取所有的Fragment
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        //获取当前位置
        int fragmentIndex = mViewPager.getCurrentItem();

        //重新加载fragment数据
        reLoadFragmentDatas(fragmentIndex, fragmentList);
    }

    private void reLoadFragmentDatas(int index, List<Fragment> fragmentList) {
        if (CommonUtils.collectionCheckIndex(fragmentList, index)) {
            if (index == 0) {
                ((SearchSubFragment) fragmentList.get(index)).reLoadNewData();
                ((SearchSubFragment) fragmentList.get(index + 1)).reLoadNewData();
            } else if (index == fragmentList.size() - 1) {
                ((SearchSubFragment) fragmentList.get(index)).reLoadNewData();
                ((SearchSubFragment) fragmentList.get(index - 1)).reLoadNewData();
            } else {
                ((SearchSubFragment) fragmentList.get(index - 1)).reLoadNewData();
                ((SearchSubFragment) fragmentList.get(index)).reLoadNewData();
                ((SearchSubFragment) fragmentList.get(index + 1)).reLoadNewData();
            }
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDelete;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mTvDelete = (TextView) itemView.findViewById(R.id.id_tv_delete);
        }
    }

    @Override
    protected void onDestroy() {
        SearchKeyWordsUtils.saveNewKeyWords(this,mTempSearchWords);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
