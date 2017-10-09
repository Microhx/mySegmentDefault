package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NewToutiaoListData;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 20:46 <p>
 * interface :
 */

public class HomeRecyclerAdapter extends BaseRecyclerAdapter<NewToutiaoListData> {

    public HomeRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NewToutiaoListData item, int position) {
        HomeRecyclerViewHolder homeHolder = (HomeRecyclerViewHolder) holder;
        homeHolder.id_tv_title.setText(item.title);
        homeHolder.id_tv_vote.setText(item.votesWord);
        homeHolder.id_tv_comment.setText(item.comments);
        homeHolder.id_tv_tag.setText(CommonUtils.getToutiaoTagTagList(item.newsTypes));

        if(null == item.user){
            homeHolder.id_tv_time.setText(item.createdDate);
        }else{
            homeHolder.id_tv_time.setText(item.user.name + " " + item.createdDate);
        }

        if(TextUtils.isEmpty(item.readFirstImg)){
            homeHolder.id_iv_icon.setVisibility(View.GONE);
        }else{
            homeHolder.id_iv_icon.setVisibility(View.VISIBLE);
            ImageUtils.showUrlImage(item.readFirstImg,homeHolder.id_iv_icon);
        }

        homeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(mContext,SchemeActivity.class);
                _intent.setData(Uri.parse(item.originPath));
                _intent.putExtra("inner",true);
                mContext.startActivity(_intent);
            }
        });
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_title)
        TextView id_tv_title;

        @Bind(R.id.id_tv_time)
        TextView id_tv_time;

        @Bind(R.id.id_tv_vote)
        TextView id_tv_vote;

        @Bind(R.id.id_tv_comment)
        TextView id_tv_comment;

        @Bind(R.id.id_tv_tag)
        TextView id_tv_tag;

        @Bind(R.id.id_iv_icon)
        ImageView id_iv_icon;

        public HomeRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
