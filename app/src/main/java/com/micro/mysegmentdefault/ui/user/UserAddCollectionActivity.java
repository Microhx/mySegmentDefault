package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middle.view.AbsUserAddCollectView;
import com.micro.mysegmentdefault.middleimpl.adapter.UserCollectRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAddCollectPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户添加收藏 & 创建收藏夹界面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 15:10 <p>
 * interface :
 */

public class UserAddCollectionActivity extends BaseRefreshActivity<UserAddCollectPresenter,UserCollectModel,UserCollectEntity.CollectItem> implements AbsUserAddCollectView<UserCollectEntity.CollectItem> {

    public static final String OBJECT_ID = "objectId" ;
    public static final String OBJECT_TAG = "objectTag";

    //生成新的文件夹
    public static final int CODE_FOR_ADD_COLLECTION = 0x01 ;

    private String mObjectId ;
    private int mObjectTag ;

    private UserCollectRecyclerAdapter mUserCollectAdapter ;

    private boolean mIsBookMarked ;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initTopRecyclerLayout(RelativeLayout layout) {
        layout.setVisibility(View.VISIBLE);

        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        tv.setText(R.string.add_collection);
        tv.setTextColor(getResources().getColor(R.color.app_theme_color));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserCollectionTags();
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.dip2px(getContext(),40));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        layout.addView(tv,params);
    }


    /**
     * 添加新的标签
     */
    private void addUserCollectionTags() {
        goForResult(UserNewCollectionTagActivity.class,CODE_FOR_ADD_COLLECTION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.d("--|||-->>" + requestCode + "--->>>>" + resultCode);

        if(requestCode == CODE_FOR_ADD_COLLECTION && resultCode == RESULT_OK) {
            String id = data.getStringExtra("id");
            String name = data.getStringExtra("title");
            String isPrivate = data.getStringExtra("isPrivate");

            UserCollectEntity.CollectItem item = new UserCollectEntity.CollectItem();
            item.isBookmarked = "0";
            item.num = "0";
            item.id = id ;
            item.isPrivate = isPrivate;
            item.title = name;

            mUserCollectAdapter.addItem(0,item);
            //滑动到第一行
            mRecyclerView.smoothScrollToPosition(0);
        }

    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_collection);

        mTvRightMessage.setVisibility(View.VISIBLE);
        mTvRightMessage.setText(R.string.str_commit);
    }

    public static void start(Context clazz , int tag, String mObjectId) {
        Intent _intent = new Intent(clazz,UserAddCollectionActivity.class);
        _intent.putExtra(OBJECT_ID,mObjectId);
        _intent.putExtra(OBJECT_TAG,tag);
        clazz.startActivity(_intent);
    }


    /**
     * 提交收藏申请
     * @param v
     */
    @OnClick(R.id.id_tv_right)
    public void onCollectionSubmit(View v) {
        Map<String,String> targetMap = mUserCollectAdapter.getSelectCollectIds();
        mIsBookMarked = !targetMap.isEmpty();

        mPresenter.addUserCollectDataEntity(mObjectId,mObjectTag,targetMap);
    }


    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mObjectId = getIntent().getStringExtra(OBJECT_ID);
        mObjectTag = getIntent().getIntExtra(OBJECT_TAG,0);
    }

    @Override
    protected String getDefaultChannel() {
        return mObjectId;
    }

    @Override
    protected int getCommonType() {
        return mObjectTag ;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        mUserCollectAdapter = new UserCollectRecyclerAdapter(this);
        return mUserCollectAdapter;
    }

    /**
     * 不得刷新
     */
    @Override
    public void onRefreshing() {
        mRefreshLayout.onComplete();
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addUserCollectionSuccess(String number) {
        CollectionMessageEvent event = new CollectionMessageEvent();
        event.isBookMarked = mIsBookMarked;
        event.number = number;
        event.type = 1 ;

        EventBus.getDefault().post(event);

        finish();
    }

    @Override
    public void addUserCollectionError() {
        showToast(R.string.str_operation_error);
    }
}
