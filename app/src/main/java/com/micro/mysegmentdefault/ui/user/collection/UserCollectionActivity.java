package com.micro.mysegmentdefault.ui.user.collection;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserAddCollectView;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAddCollectPresenter;
import com.micro.mysegmentdefault.ui.user.UserNewCollectionTagActivity;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 我的收藏 界面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 14:16 <p>
 * interface :
 */

public class UserCollectionActivity extends AbBaseAttentionActivity<UserAddCollectPresenter, UserCollectModel, UserCollectEntity.CollectItem> implements AbsUserAddCollectView<UserCollectEntity.CollectItem> {

    private static final int CODE_FOR_ADD_COLLECTION = 0x01 ;
    private static final int CODE_FOR_DELETE_COLLECTION = 0x02;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    protected void defaultSettingLayoutDecoration() {
        //we don't need the itemDecor
    }

    @Override
    protected void initTopRecyclerLayout(RelativeLayout layout) {
        layout.setVisibility(View.VISIBLE);
        layout.removeAllViews();

        View rootView = View.inflate(this,R.layout.item_user_add_collect,null);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForResult(UserNewCollectionTagActivity.class,CODE_FOR_ADD_COLLECTION);
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.dip2px(getContext(),60));
        layout.addView(rootView,params);

        mTvTitle.setText(R.string.str_collect);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.user_add_collection_item;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final UserCollectEntity.CollectItem item, int position) {
        holder.setTextView(R.id.id_tv_title, item.title);
        holder.setTextView(R.id.id_tv_content, "共" + item.num + "个条目");
        holder.setViewGone(R.id.id_iv_lock, CommonUtils.safeParseInt(item.isPrivate) == 0);
        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCollectionDetailActivity.start(UserCollectionActivity.this,item.id,"true",item.num,item.isPrivate,CODE_FOR_DELETE_COLLECTION);
            }
        }) ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

            mBaseRecyclerAdapter.addItem(0,item);
            //滑动到第一行
            mRecyclerView.smoothScrollToPosition(0);
        }else if(requestCode == CODE_FOR_DELETE_COLLECTION && resultCode == RESULT_OK) {

            mBaseRecyclerAdapter.removeItem(getCurrentIndex(data.getStringExtra("collectId")));
        }
    }

    private int getCurrentIndex(String collectId) {
        int index = -1 ;
        for(int i = 0 , len = mBaseRecyclerAdapter.getItems().size(); i < len ; ++i){
            UserCollectEntity.CollectItem item = mBaseRecyclerAdapter.getItem(i);
            if(null != item && collectId.equals(item.id)){
                index = i ;
                break;
            }
        }

        return index;
    }


    //-------------------------AbsUserCollectView implements-------------------
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addUserCollectionSuccess(String number) {

    }

    @Override
    public void addUserCollectionError() {

    }
}
