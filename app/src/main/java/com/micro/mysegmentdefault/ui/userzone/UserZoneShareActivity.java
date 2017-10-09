package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.NewToutiaoListData;
import com.micro.mysegmentdefault.middleimpl.mvp.model.HomeModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ToutiaoPresenter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 用户空间 --> 用户分享<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/2 - 20:29 <p>
 * interface :
 */

public class UserZoneShareActivity extends AbBaseAttentionActivity<ToutiaoPresenter, HomeModel, NewToutiaoListData> {

    @Override
    protected int getItemLayoutId() {
        return R.layout.home_item_layout;
    }

    @Override
    protected int getCommonType() {
        return Integer.MIN_VALUE ;
    }

    private String uid;

    public static void start(Context ctx, String uid) {
        ctx.startActivity(new Intent(ctx,UserZoneShareActivity.class).putExtra("uid",uid));
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_user_share);
    }

    @Override
    protected String getDefaultChannel() {
        return uid;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final NewToutiaoListData item, int position) {
        holder.setTextView(R.id.id_tv_title,item.title).
                setTextView(R.id.id_tv_time,item.user.name + " " + item.createdDate).
                setTextView(R.id.id_tv_vote,item.votesWord).
                setTextView(R.id.id_tv_comment,item.comments).
                setTextView(R.id.id_tv_tag, CommonUtils.getToutiaoTagTagList(item.newsTypes));

        if(TextUtils.isEmpty(item.readFirstImg)) {
            holder.setViewGone(R.id.id_iv_icon,true);
        }else{
            holder.setViewGone(R.id.id_iv_icon,false);
            holder.setImageView(R.id.id_iv_icon,item.readFirstImg);
        }

        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(item.url)) {
                    Intent _intent = new Intent(mContext, SchemeActivity.class);
                    _intent.setData(Uri.parse(Api.WEB_URL + item.url));
                    mContext.startActivity(_intent);
                }else{
                    ToastUtils.showMessage(mContext,"url无效");
                }
            }
        }) ;
    }
}
