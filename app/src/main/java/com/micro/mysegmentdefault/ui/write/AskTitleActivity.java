package com.micro.mysegmentdefault.ui.write;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsAksTagView;
import com.micro.mysegmentdefault.middleimpl.adapter.SelectSameQuestionAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.SearchSubModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.SearchSubPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.recyclerview.RecycleViewDivider2;
import com.micro.mysegmentdefault.view.recyclerview.RecyclerRefreshLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 创建问题 选择问题的title
 */
public class AskTitleActivity extends BaseActivity<SearchSubPresenter, SearchSubModel> implements BaseRefreshView<SearchDataEntity.SearchItem>, RecyclerRefreshLayout.SuperRefreshLayoutListener, AbsAksTagView<SearchDataEntity.SearchItem> {

    private final static int PAGE_STEP = 1;

    @Bind(R.id.id_et_title)
    TextInputEditText mTitleContent;

    @Bind(R.id.id_tv_search_hint)
    TextView mSearchHint;

    @Bind(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.id_refresh_layout)
    RecyclerRefreshLayout mRefreshLayout;

    private SelectSameQuestionAdapter mSelectSameQuestionAdapter;

    private PageEntity mPageEntity;

    private String mKeyWords;


    @OnTextChanged(R.id.id_et_title)
    public void onTextChange(CharSequence s, int start, int before, int count) {

        if (!TextUtils.isEmpty(s) && !s.equals(mKeyWords)) {
            mKeyWords = String.valueOf(s);
            Message msg = mSearchDelayHandler.obtainMessage();
            msg.obj = mKeyWords ;

            //清除掉600ms之内所有的请求消息
            mSearchDelayHandler.removeCallbacksAndMessages(null);
            mSearchDelayHandler.sendMessageDelayed(msg,800);
        }
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }

    @Override
    public void finish() {
        String str = mTitleContent.getText().toString().trim();
        if(TextUtils.isEmpty(str)) {
            setResult(RESULT_CANCELED);
            super.finish();
        }else{
            setResult(RESULT_OK,new Intent().putExtra("title",str));
            super.finish();
        }
    }

    private void checkRecycleViewVisible() {
        if (View.GONE == mRefreshLayout.getVisibility()) {
            mRefreshLayout.setVisibility(View.VISIBLE);
            mSearchHint.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initViews() {
        String originalTitle = getIntent().getStringExtra("title");
        if(!TextUtils.isEmpty(originalTitle)) {
            mTitleContent.setText(originalTitle);
        }

        mSelectSameQuestionAdapter = new SelectSameQuestionAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider2(this, LinearLayout.VERTICAL));
        mRecyclerView.setAdapter(mSelectSameQuestionAdapter);

        mRefreshLayout.setSuperRefreshLayoutListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ask_title;
    }

    @Override
    public void getCommonListDatas(int startPages, List<SearchDataEntity.SearchItem> mDataList, PageEntity entity) {
        if (startPages == PAGE_STEP) {
            if (CommonUtils.collectionIsNull(mDataList)) {
                LogUtils.d("startPages:" + startPages + ",mDataList is null");
                mSelectSameQuestionAdapter.setState(BaseRecyclerAdapter.STATE_NO_MORE, true);
                return;
            }
        }

        if (null == entity) {
            mPageEntity = null;
            mSelectSameQuestionAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
            mRefreshLayout.setCanLoadMore(false);
        } else {
            mPageEntity = entity;
            mSelectSameQuestionAdapter.setState(entity.current <= entity.total ? BaseRecyclerAdapter.STATE_NO_MORE : BaseRecyclerAdapter.STATE_LOADING, true);
            mRefreshLayout.setCanLoadMore(entity.current < entity.total);
            mRefreshLayout.onComplete();
        }

        mSelectSameQuestionAdapter.addAll(mDataList);
    }

    @Override
    public void getRequestError(int startPage) {
        mSelectSameQuestionAdapter.setState(BaseRecyclerAdapter.STATE_LOAD_ERROR, true);
    }

    @Override
    public void onRefreshing() {
        mRefreshLayout.onComplete();
    }

    @Override
    public void onLoadMore() {
        if (null == mPresenter) return;

        mSelectSameQuestionAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
        mPresenter.getCommonListDatas(1, mKeyWords, getCurrentPage());
    }

    private int getCurrentPage() {
        if (null == mPageEntity) return PAGE_STEP;
        return mPageEntity.next;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchDelayHandler.removeCallbacksAndMessages(null);
    }

    private Handler mSearchDelayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(null != msg) {
                checkRecycleViewVisible();
                mPageEntity = null;
                mSelectSameQuestionAdapter.clear();

                mPresenter.getCommonListDatas(1, mKeyWords, PAGE_STEP);
            }
        }
    };

    @Override
    public void showUserNewTag(boolean result) {}
}
