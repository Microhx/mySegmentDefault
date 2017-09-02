package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ArticleDataEntity;
import com.micro.mysegmentdefault.ui.ArticleDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:50 <p>
 * interface :
 */

public class NewsRecyclerAdapter extends BaseRecyclerAdapter<ArticleDataEntity.Item> {

    public NewsRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final ArticleDataEntity.Item item, int position) {
        NewsViewHolder newsHolder = (NewsViewHolder) holder;

        newsHolder.id_tv_title.setText(item.title);
        newsHolder.id_tv_content.setText(item.excerpt);
        newsHolder.id_tv_vote_comment.setText(item.votes + "人点赞  " + item.bookmarks + "人收藏");
        newsHolder.id_tv_author.setText(item.user.name);

        newsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDetailActivity.start(item.id,ArticleDetailActivity.class);
            }
        });
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.id_tv_title)
        TextView id_tv_title;

        @Bind(R.id.id_tv_content)
        TextView id_tv_content;

        @Bind(R.id.id_tv_vote_comment)
        TextView id_tv_vote_comment;

        @Bind(R.id.id_tv_author)
        TextView id_tv_author;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
