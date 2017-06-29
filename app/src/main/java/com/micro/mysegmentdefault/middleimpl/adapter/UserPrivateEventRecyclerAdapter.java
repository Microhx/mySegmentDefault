package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserPrivateEventDataEntity;
import com.micro.mysegmentdefault.ui.user.message.UserPrivateEventDetailActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 18:18 <p>
 * interface :
 */

public class UserPrivateEventRecyclerAdapter extends BaseRecyclerAdapter<UserPrivateEventDataEntity.DataItem> {

    public UserPrivateEventRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserPrivateEventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_private_event_item_layuout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final UserPrivateEventDataEntity.DataItem item, int position) {
        UserPrivateEventViewHolder privateHolder = (UserPrivateEventViewHolder) holder;

        ImageUtils.showUrlImage(item.triggerUser.get(0).avatarUrl,privateHolder.mIconImage);
        privateHolder.mTvTime.setText(item.lastMessage.createdDate);
        privateHolder.mTvTitle.setText(item.triggerUser.get(0).name);
        privateHolder.mTvContent.setText(item.lastMessage.content.content);

        //设置点击事件
        privateHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPrivateEventDetailActivity.start(item.id,item.triggerUser.get(0).avatarUrl);
            }
        });
    }


    public class UserPrivateEventViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_image)
        CircleImageView mIconImage ;

        @Bind(R.id.id_tv_time)
        TextView mTvTime ;

        @Bind(R.id.id_tv_title)
        TextView mTvTitle;

        @Bind(R.id.id_tv_content)
        TextView mTvContent;


        public UserPrivateEventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
