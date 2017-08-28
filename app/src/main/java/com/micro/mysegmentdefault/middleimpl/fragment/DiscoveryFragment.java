package com.micro.mysegmentdefault.middleimpl.fragment;

import android.text.TextUtils;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.middle.DiscoverContract;
import com.micro.mysegmentdefault.middleimpl.adapter.SlideViewPagerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.DiscoverModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.DiscoverPresenter;
import com.micro.mysegmentdefault.ui.ActionActivity;
import com.micro.mysegmentdefault.ui.ActivityDetailActivity;
import com.micro.mysegmentdefault.ui.NoteListActivity;
import com.micro.mysegmentdefault.ui.TopUserListActivity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.widget.CustomViewPager;
import com.micro.mysegmentdefault.view.widget.ViewPagerIndicator;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 发现fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:23 <p>
 * interface :
 */

public class DiscoveryFragment extends BaseFragment<DiscoverPresenter,DiscoverModel> implements DiscoverContract.DiscoverView, CustomViewPager.onLeftOrRightListener, SlideViewPagerAdapter.onImageViewClickListener {

    @Bind(R.id.id_view_pager)
    CustomViewPager mCustomViewPager;

    @Bind(R.id.id_pager_indicator)
    ViewPagerIndicator mViewPagerIndicator;

    SlideViewPagerAdapter mSlideViewPagerAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {
        mPresenter.setVM(this,mModel);
        mPresenter.getRecommendActivityList();
    }

    @Override
    public void showDiscoverData(DiscoverDataEntity dataEntity) {
        if(null != dataEntity ){
            mSlideViewPagerAdapter = new SlideViewPagerAdapter(getContext(),dataEntity.data.rows,this);
            mViewPagerIndicator.setPagerAdapter(mSlideViewPagerAdapter);

            mCustomViewPager.setAdapter(mSlideViewPagerAdapter);
            mCustomViewPager.setLeftOrRightListener(this);
       }
    }

    @Override
    public void onErrorWhenReceiveDiscoverData() {}

    @OnClick(R.id.id_card_note)
    public void goToNoteListActivity(View v) {
        goWithActivity(NoteListActivity.class);
    }

    @OnClick(R.id.id_card_lasted)
    public void goToActionListActivity(View v) {
        goWithActivity(ActionActivity.class);
    }

    @OnClick(R.id.id_card_topuser)
    public void goToTopUserListActivity(View v) {
        goWithActivity(TopUserListActivity.class);
    }

    @Override
    public void onPageScrolled(int startPosition, int endPosition, float positionOffset, boolean isRight) {
            mViewPagerIndicator.onPageScrolled(startPosition,endPosition,positionOffset,isRight);
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onClick(DiscoverDataEntity.DiscoverItem item) {
        if(null != item && !TextUtils.isEmpty(item.id)) {
            CommonWebActivity.start(item.id, ActivityDetailActivity.class);
        }else {
            ToastUtils.showMessage(getActivity(),"Id缺失");
        }
    }
}
