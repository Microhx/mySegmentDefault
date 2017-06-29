package com.micro.mysegmentdefault.middleimpl.fragment;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.middle.DiscoverContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.DiscoverModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.DiscoverPresenter;
import com.micro.mysegmentdefault.ui.ActionActivity;
import com.micro.mysegmentdefault.ui.NoteListActivity;
import com.micro.mysegmentdefault.ui.TopUserListActivity;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.SlideshowView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 发现fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:23 <p>
 * interface :
 */

public class DiscoverFragment extends BaseFragment<DiscoverPresenter,DiscoverModel> implements DiscoverContract.DiscoverView {

    @Bind(R.id.id_slide_view)
    SlideshowView mSlideView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {
        mPresenter.setVM(this,mModel);
        //mPresenter.getRecommendActivityList();
    }

    @Override
    public void showDiscoverData(DiscoverDataEntity dataEntity) {
        LogUtils.d("--------showDiscoverData--------->>" + dataEntity.status);
        if(null != dataEntity ){
            mSlideView.setDatas(dataEntity.data.rows);
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

}
