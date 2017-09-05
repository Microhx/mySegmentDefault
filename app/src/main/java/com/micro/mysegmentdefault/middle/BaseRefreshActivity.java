package com.micro.mysegmentdefault.middle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.recyclerview.RecycleViewDivider2;
import com.micro.mysegmentdefault.view.recyclerview.RecyclerRefreshLayout;
import com.micro.mysegmentdefault.view.widget.EmptyLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.micro.mysegmentdefault.view.widget.EmptyLayout.NETWORK_ERROR;
import static com.micro.mysegmentdefault.view.widget.EmptyLayout.NODATA;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 23:10 <p>
 * interface :
 */

public abstract class BaseRefreshActivity<T extends BaseRefreshPresenter,
        E extends BaseRefreshModel,
        D extends BaseDataInterface>
        extends BaseActivity<T, E>
        implements RecyclerRefreshLayout.SuperRefreshLayoutListener, BaseRefreshView<D> {

    //默认page值
    private static final int PAGE_STEP = 1;

    @Bind(R.id.id_title_content)
    public FrameLayout mTitleContent;

    @Bind(R.id.id_layout_up_recycler)
    public RelativeLayout mTopRecyclerLayout;

    @Bind(R.id.id_recycler_view)
    public RecyclerView mRecyclerView;

    @Bind(R.id.id_refresh_layout)
    public RecyclerRefreshLayout mRefreshLayout;

    @Bind(R.id.id_empty_layout)
    public EmptyLayout mEmptyLayout;

    @Bind(R.id.id_tag_title)
    public TextView mTvTitle;

    @Bind(R.id.id_tv_right)
    public TextView mTvRightMessage;

    @Bind(R.id.id_layout_title)
    public RelativeLayout mTitleLayout;

    @Bind(R.id.id_float_button)
    public FloatingActionButton mActionButton;

    @Bind(R.id.id_iv_right)
    public ImageButton mIvRightButton ;

    @Bind(R.id.id_bottom_layout)
    public LinearLayout mBottomLayout ;

    protected PageEntity mPageEntity;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_refresh;
    }

    protected BaseRecyclerAdapter<D> mBaseRecyclerAdapter;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initViews() {

        initTitleSetting(mTitleContent);
        initTopRecyclerLayout(mTopRecyclerLayout);

        initBottomLayoutView();

        mBaseRecyclerAdapter = getRecyclerAdapter();
        defaultSettingLayoutManager();
        defaultSettingLayoutDecoration();
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.app_theme_color));
        mRefreshLayout.setSuperRefreshLayoutListener(this);

        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), getCurrentPage());
    }

    //设置顶部View 说点生命
    protected void initBottomLayoutView() {}

    /**
     * RecyclerView顶端Layout
     * @param layout
     */
    protected void initTopRecyclerLayout(RelativeLayout layout) {}

    /**
     * 设置头部
     *
     * @param mTitleContent
     */
    protected void initTitleSetting(FrameLayout mTitleContent) {}


    /**
     * 这里默认设置 RecyclerView的LayoutManager
     */
    protected void defaultSettingLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    protected void defaultSettingLayoutDecoration() {
        mRecyclerView.addItemDecoration(new RecycleViewDivider2(this, LinearLayout.VERTICAL));
    }


    protected abstract BaseRecyclerAdapter<D> getRecyclerAdapter();

    @Override
    public void onRefreshing() {
        mPageEntity = null;
        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), getCurrentPage());
    }

    @Override
    public void onLoadMore() {
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
                if(showEmptyPageWhenDataIsNull()) {
                    mEmptyLayout.setVisibility(View.VISIBLE);
                    mEmptyLayout.setErrorType(NODATA);
                }

                return;
            }

            mEmptyLayout.dismiss();
            mBaseRecyclerAdapter.clear();
        }

        mPageEntity = entity;
        if(null == entity) {
            mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_HIDE,true);
            mRefreshLayout.setCanLoadMore(false);
        }else {
            mBaseRecyclerAdapter.setState(entity.current <= entity.total ? BaseRecyclerAdapter.STATE_NO_MORE :
                    BaseRecyclerAdapter.STATE_LOADING, true);
            mRefreshLayout.setCanLoadMore(entity.current < entity.total);
        }

        mBaseRecyclerAdapter.addAll(mDataList);
        //加载结束
        mRefreshLayout.onComplete();

        afterRequestFinish();
    }

    /**
     * 请求之后所需要的处理
     */
    protected void afterRequestFinish() {
    }


    /**
     * 当不存在数据时，是否应该显示empty页面
     * @return
     */
    protected boolean showEmptyPageWhenDataIsNull(){
        return true;
    }



    @Override
    public void getRequestError(int startPage) {
        if (startPage > PAGE_STEP) {
            //加载结束
            mRefreshLayout.onComplete();
            mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_LOAD_ERROR, true);
        } else {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mEmptyLayout.setErrorType(NETWORK_ERROR);
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

    protected void checkLayoutVisible() {
        if (mBaseRecyclerAdapter.getCount() > 0) {
            mEmptyLayout.setVisibility(View.GONE);
        } else if (mEmptyLayout.getVisibility() != View.VISIBLE) {
            mEmptyLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.id_iv_back)
    public void onCallBack(View v) {
        finish();
    }


}
