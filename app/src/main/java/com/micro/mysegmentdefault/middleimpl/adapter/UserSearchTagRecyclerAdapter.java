package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.entity.TagDetailDataEntity;
import com.micro.mysegmentdefault.ui.UserTagDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 23:28 <p>
 * interface :
 */

public class UserSearchTagRecyclerAdapter extends BaseRecyclerAdapter<TagDataEntity.Item> {

    public UserSearchTagRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new SearchTagViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_search_tag_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final TagDataEntity.Item item, int position) {
        SearchTagViewHolder searchHolder = (SearchTagViewHolder) holder;
        searchHolder.mtvResult.setText(item.name);

        searchHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserTagDetailActivity.start(item.name, item.id);
            }
        });

    }

    class SearchTagViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_result)
        TextView mtvResult;

        public SearchTagViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
