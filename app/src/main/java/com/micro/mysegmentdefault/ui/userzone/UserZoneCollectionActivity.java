package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserAddCollectView;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAddCollectPresenter;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/2 - 20:49 <p>
 * interface :
 */

public class UserZoneCollectionActivity extends
        AbBaseAttentionActivity<UserAddCollectPresenter, UserCollectModel, UserCollectEntity.CollectItem>
        implements AbsUserAddCollectView<UserCollectEntity.CollectItem> {

    private String uid;

    public static void start(Context ctx, String uid) {
        ctx.startActivity(new Intent(ctx, UserZoneCollectionActivity.class).putExtra("uid", uid));
    }

    @Override
    protected int getCommonType() {
        return Integer.MIN_VALUE;
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_user_collection);
    }

    @Override
    protected String getDefaultChannel() {
        return uid;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.user_add_collection_item;
    }

    /*
    * holder.setTextView(R.id.id_tv_title, item.title);
        holder.setTextView(R.id.id_tv_content, "共" + item.num + "个条目");
        holder.setViewGone(R.id.id_iv_lock, CommonUtils.safeParseInt(item.isPrivate) == 0);
        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCollectionDetailActivity.start(item.id,item.num,item.isPrivate);
            }
        }) ;
    * */

    @Override
    protected void convertData(ViewHolderHelper holder, UserCollectEntity.CollectItem item, int position) {
        holder.setTextView(R.id.id_tv_title, item.title).
                setTextView(R.id.id_tv_content, "共" + item.num + "个条目");
        holder.setViewVisiable(R.id.id_iv_lock, CommonUtils.safeParseInt(item.isPrivate) > 0);
    }


    /*--------------mvp implements-------------------*/
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
