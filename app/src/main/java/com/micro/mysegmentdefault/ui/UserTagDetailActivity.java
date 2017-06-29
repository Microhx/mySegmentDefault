package com.micro.mysegmentdefault.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.entity.TagDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.TagDetailContract;
import com.micro.mysegmentdefault.middleimpl.adapter.TagDetailPageAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TagDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TagDetailPresenter;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 标签详情界面
 */
public class UserTagDetailActivity extends BaseActivity<TagDetailPresenter,TagDetailModel> implements TagDetailContract.DetailView {

    public static final String TAG_NAME = "tag_name";
    public static final String TAG_ID = "tag_id";


    @Bind(R.id.id_view_pager)
    ViewPager mViewPager ;

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout ;

    @Bind(R.id.id_tv_tag_name)
    TextView mTvTagName ;

    @Bind(R.id.id_tag_status)
    TextView mTvTagStatus ;

    @Bind(R.id.id_iv_tag)
    ImageView mIvTagImage ;

    @Bind(R.id.id_tv_introduce)
    TextView mTvIntroduce ;

    private String mTag ;
    private String mTagId ;

    private TagDetailPageAdapter mDetailPageAdapter;

    //是否关注
    private boolean mTagHasFollowed ;


    public static void start(String tagName , String tagId) {
        Intent _intent = new Intent(SegmentApplication.getApplication(),UserTagDetailActivity.class);
        _intent.putExtra(TAG_NAME,tagName);
        _intent.putExtra(TAG_ID,tagId);
        _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SegmentApplication.getApplication().startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        if(null != getIntent()) {
            mTag = getIntent().getStringExtra(TAG_NAME);
            mTagId = getIntent().getStringExtra(TAG_ID);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_tag_detail;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.getTagDetailInfo(mTag);
    }

    @Override
    protected void initViews() {
        List<String> _titleList = Arrays.asList(getResources().getStringArray(R.array.tag_detail));
        mDetailPageAdapter = new TagDetailPageAdapter(_titleList,mTagId,getSupportFragmentManager());

        mViewPager.setAdapter(mDetailPageAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDataError() {
        ToastUtils.showMessage(this,getString(R.string.data_request_error));
    }

    @Override
    public void onDataSuccess(Object o) {
        TagDetailDataEntity dataEntity = (TagDetailDataEntity) o;
        if(null == dataEntity || dataEntity.status != 0) {
            onDataError();
        }else {
            TagDetailDataEntity.Data data = dataEntity.data;
            if(TextUtils.isEmpty(data.thumbnailUrl)){
                mIvTagImage.setVisibility(View.GONE);
            }else {
                ImageUtils.showUrlImage(data.thumbnailUrl,mIvTagImage);
            }

            mTvTagName.setText(data.name);
            mTvTagStatus.setText(data.isFollowed? R.string.has_followed : R.string.has_not_followed);
            mTvTagStatus.setBackground(data.isFollowed? getResources().getDrawable(R.drawable.tag_attention_drawable) :
                                                        getResources().getDrawable(R.drawable.tag_unattention_drawable));
            mTvIntroduce.setText(data.excerpt);
            mTagHasFollowed = data.isFollowed ;
        }
    }

    @OnClick(R.id.id_tag_status)
    public void onCall(View v) {
        if(UserLogic.checkUserLogin()) {
            if(!mTagHasFollowed) {
                mPresenter.followTags(mTagId);
            }else { //用户关注
                mPresenter.cancelFollowTags(mTagId);
            }
        }
    }


    @Override
    public void onFollowTagStatus(boolean success) {
        if(success) {
            mTagHasFollowed = true ;
            mTvTagStatus.setText(R.string.has_followed);
            mTvTagStatus.setBackground(getResources().getDrawable(R.drawable.tag_attention_drawable));

            sendFollowTagEventByType(2);
        }else {
            ToastUtils.showMessage(this,R.string.tag_follow_error);
        }
    }

    @Override
    public void onCancelFollowTagStatus(boolean success) {
        if(success) {
            mTagHasFollowed = false ;
            mTvTagStatus.setText(R.string.has_not_followed);
            mTvTagStatus.setBackground(getResources().getDrawable(R.drawable.tag_unattention_drawable));

            sendFollowTagEventByType(3);
        }else {
            ToastUtils.showMessage(this,R.string.tag_not_follow_error);
        }
    }


    private void sendFollowTagEventByType(int type) {
        MessageEvent event = new MessageEvent();
        event.item = getTagItem();
        event.type = type;
        EventBus.getDefault().post(event);
    }

    private TagDataEntity.Item getTagItem() {
        TagDataEntity.Item item = new TagDataEntity.Item();
        item.isFollowed = true;
        item.name = mTag;
        item.id = mTagId;
        return item ;
    }

}
