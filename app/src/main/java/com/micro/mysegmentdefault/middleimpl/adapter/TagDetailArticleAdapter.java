package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDetailArticleEntity;
import com.micro.mysegmentdefault.ui.ArticleDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 17:49 <p>
 * interface :
 *
 */

@Deprecated
public class TagDetailArticleAdapter extends BaseRecyclerAdapter<TagDetailArticleEntity.Item> {

    public TagDetailArticleAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_article_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final TagDetailArticleEntity.Item item, int position) {
        ArticleViewHolder articleHolder = (ArticleViewHolder) holder;
        articleHolder.mTvTitle.setText(item.title);
        articleHolder.mTvContent.setText(item.excerpt);
        articleHolder.mVoteComment.setText(item.votes + "人点赞 " + item.comments + "人收藏");
        articleHolder.mAuthor.setText(item.user.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDetailActivity.start(item.id,ArticleDetailActivity.class);
            }
        });
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_title)
        TextView mTvTitle;

        @Bind(R.id.id_tv_content)
        TextView mTvContent;

        @Bind(R.id.id_tv_vote_comment)
        TextView mVoteComment;

        @Bind(R.id.id_tv_author)
        TextView mAuthor;

        public ArticleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }
}

