package com.micro.mysegmentdefault.middle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.Constant;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.recyclerview.RecycleViewDivider2;
import com.micro.mysegmentdefault.view.recyclerview.RecyclerRefreshLayout;
import com.micro.mysegmentdefault.view.widget.EmptyLayout;

import java.util.List;

import butterknife.Bind;

import static com.micro.mysegmentdefault.view.widget.EmptyLayout.NETWORK_ERROR;
import static com.micro.mysegmentdefault.view.widget.EmptyLayout.NODATA;

/**
 * author : micro_hx <p>
 * desc : 上下拉加载 刷新的Fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/22 - 15:59 <p>
 * interface :
 */


public abstract class BaseRefreshFragment<
                      T extends BaseRefreshPresenter,
                      E extends BaseRefreshModel,
                      D extends BaseDataInterface>

        extends BaseFragment<T, E>
        implements RecyclerRefreshLayout.SuperRefreshLayoutListener, BaseRefreshView<D>,BaseRecyclerAdapter.OnFooterClickListener {

    //默认page值
    protected static final int PAGE_STEP = Constant.PAGE_STEP;

    //当前Page值
    private int mStartPages = 1;

    @Bind(R.id.id_recycler_view)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.id_refresh_layout)
    protected RecyclerRefreshLayout mRefreshLayout;

    @Bind(R.id.id_empty_layout)
    protected EmptyLayout mEmptyLayout;

    @Bind(R.id.id_bottom_layout)
    protected LinearLayout mBottomLayout;

    private PageEntity mPageEntity;

    protected BaseRecyclerAdapter<D> mBaseRecyclerAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_base_refresh;
    }

    @Override
    protected void initPresenter() {
        if (null == mPresenter) return;
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initViews() {
        initBottomLayout();

        mBaseRecyclerAdapter = getRecyclerAdapter();
        mBaseRecyclerAdapter.setOnFooterClickListener(this);

        defaultSettingLayoutManager();
        defaultSettingLayoutDecoration();

        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.app_theme_color));
        mRefreshLayout.setSuperRefreshLayoutListener(this);

        if (null != mPresenter && shouldStartRequest())
            mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), mStartPages);
    }

    /**
     * 顶部View#BottomLayout
     */
    protected void initBottomLayout() {

    }


    protected void defaultSettingLayoutDecoration() {
        mRecyclerView.addItemDecoration(new RecycleViewDivider2(getContext(), LinearLayout.VERTICAL));
    }

    /**
     * 这里默认设置 RecyclerView的LayoutManager
     */
    protected void defaultSettingLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    protected abstract BaseRecyclerAdapter<D> getRecyclerAdapter();

    @Override
    public void onRefreshing() {
        if (null == mPresenter) {
            mRefreshLayout.onComplete();
            return;
        }

        mPageEntity = null;
        mStartPages = PAGE_STEP;
        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), mStartPages);
    }

    @Override
    public void onLoadMore() {
        if (null == mPresenter) return;

        mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), getCurrentPage());
    }

    private int getCurrentPage() {
        if (null == mPageEntity) return PAGE_STEP;
        return mPageEntity.next;
    }

    @Override
    public void getCommonListDatas(int startPages, List<D> mDataList, PageEntity entity) {
        if (startPages == PAGE_STEP) {
            if (CommonUtils.collectionIsNull(mDataList)) {
                LogUtils.d("startPages:" + startPages + ",mDataList is null");
                whenGetNullContent();
                return;
            }

            mEmptyLayout.dismiss();
            mBaseRecyclerAdapter.clear();
        }

        if (null == entity) {
            mPageEntity = null;
            mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
            mRefreshLayout.setCanLoadMore(false);
        } else {
            mPageEntity = entity;
            mBaseRecyclerAdapter.setState(entity.current <= entity.total ? BaseRecyclerAdapter.STATE_NO_MORE : BaseRecyclerAdapter.STATE_LOADING, true);
            mRefreshLayout.setCanLoadMore(entity.current < entity.total);
        }

        mBaseRecyclerAdapter.addAll(mDataList);
        //加载结束
        mRefreshLayout.onComplete();
    }


    @Override
    public void getRequestError(int startPage) {
        if (startPage > PAGE_STEP) {
            //加载结束
            mRefreshLayout.onComplete();
            mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_LOAD_ERROR, true);
        } else {
            if(null == mEmptyLayout) return;
            whenGetNullContent();
        }
    }

    /**
     * 获取默认的channel值
     */
    protected String getDefaultChannel() {
        return "";
    }

    protected int getCommonType() {
        return 0;
    }


    /**
     * 是否开始网络请求
     * @return
     */
    protected boolean shouldStartRequest() {
        return true ;
    }

    protected void checkLayoutVisible() {
        if (mBaseRecyclerAdapter.getCount() > 0) {
            mEmptyLayout.setVisibility(View.GONE);
        } else if (mEmptyLayout.getVisibility() != View.VISIBLE) {
            mEmptyLayout.setVisibility(View.VISIBLE);
        }
    }


    private void whenGetNullContent() {
        if(mBaseRecyclerAdapter.getCount() > 0) {
            ToastUtils.showMessage(getContext(),R.string.state_load_error);
        }else {
            defaultSetEmptyPage();
        }
    }


    /**
     * 默认显示空页面
     */
    private void defaultSetEmptyPage() {
        mEmptyLayout.setVisibility(View.VISIBLE);
        mEmptyLayout.setErrorType(NODATA);
    }

    @Override
    public void onFooterClick(int state) {
        if(state == BaseRecyclerAdapter.STATE_LOAD_ERROR || state == BaseRecyclerAdapter.STATE_INVALID_NETWORK) {
            mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_LOADING, true);
            onLoadMore();
        }
    }
}
