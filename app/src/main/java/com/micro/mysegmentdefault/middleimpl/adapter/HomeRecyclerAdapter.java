package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.ui.HomeDataDetailActivity;
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

public class HomeRecyclerAdapter extends BaseRecyclerAdapter<HomeDataEntity.Item> {

    public HomeRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final HomeDataEntity.Item item, int position) {
        HomeRecyclerViewHolder homeHolder = (HomeRecyclerViewHolder) holder;
        homeHolder.id_tv_title.setText(item.title);
        homeHolder.id_tv_time.setText(item.user.name + " " + item.createdDate);
        homeHolder.id_tv_vote.setText(item.votesWord);
        homeHolder.id_tv_comment.setText(item.comments);
        homeHolder.id_tv_tag.setText(CommonUtils.getArticleTagList(item.newsTypes));

        if(TextUtils.isEmpty(item.readFirstImg)){
            homeHolder.id_iv_icon.setVisibility(View.GONE);
        }else{
            homeHolder.id_iv_icon.setVisibility(View.VISIBLE);
            ImageUtils.showUrlImage(item.readFirstImg,homeHolder.id_iv_icon);
        }

        homeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeDataDetailActivity.start(item.id,HomeDataDetailActivity.class);
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
