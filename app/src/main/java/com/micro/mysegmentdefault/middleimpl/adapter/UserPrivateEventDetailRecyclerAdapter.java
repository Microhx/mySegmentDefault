package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserPrivateEventDetailDataEntity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 22:28 <p>
 * interface :
 */

public class UserPrivateEventDetailRecyclerAdapter extends BaseRecyclerAdapter<UserPrivateEventDetailDataEntity.DataItem> {

    private String mUserPhoto ;

    public UserPrivateEventDetailRecyclerAdapter(Context ctx , String userPhoto) {
        super(ctx);
        this.mUserPhoto = userPhoto;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DetailViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_private_event_detail_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, UserPrivateEventDetailDataEntity.DataItem item, int position) {
            DetailViewHolder detailHolder = (DetailViewHolder) holder;
            detailHolder.mTvTime.setText(item.createdDate);
            detailHolder.mTvContent.setText(item.content);
            ImageUtils.showUrlImage(mUserPhoto,detailHolder.mIvIcon);
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTime;
        TextView mTvContent;
        CircleImageView mIvIcon;

        public DetailViewHolder(View itemView) {
            super(itemView);

            mTvTime = (TextView) itemView.findViewById(R.id.id_tv_time);
            mTvContent = (TextView) itemView.findViewById(R.id.id_tv_content);
            mIvIcon = (CircleImageView) itemView.findViewById(R.id.id_iv_icon);
        }
    }


}
