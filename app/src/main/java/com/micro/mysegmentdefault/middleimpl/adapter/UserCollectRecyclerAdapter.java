package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 15:36 <p>
 * interface :
 */

public class UserCollectRecyclerAdapter extends BaseRecyclerAdapter<UserCollectEntity.CollectItem> {

    public UserCollectRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserCollectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_add_collect_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final UserCollectEntity.CollectItem item, int position) {
        UserCollectViewHolder userViewHolder = (UserCollectViewHolder) holder;
        userViewHolder.mCheckBox.setChecked(CommonUtils.checkDataIsTure(item.isBookmarked));
        userViewHolder.mTvTitle.setText(item.title);
        userViewHolder.mTvSubTitle.setText("共" + item.num + "个条目");
        userViewHolder.mTvPrivate.setVisibility(CommonUtils.checkDataIsTure(item.isPrivate) ? View.VISIBLE : View.GONE);

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItemCheck(item);
            }
        });
    }

    private void selectItemCheck(UserCollectEntity.CollectItem item) {
        int position = -1;
        for (int i = 0, len = mItems.size(); i < len; ++i) {
            UserCollectEntity.CollectItem temp = mItems.get(i);
            if (item == temp) {
                position = i;
                if (CommonUtils.safeParseInt(temp.isBookmarked) == 0) {
                    temp.isBookmarked = "1";
                } else {
                    temp.isBookmarked = "0";
                }
            }
        }

        if (position >= 0) {
            notifyItemChanged(position);
        }
    }

    public Map<String,String> getSelectCollectIds() {
        Map<String,String> target = new HashMap<>();
        int index =0 ;
        for(UserCollectEntity.CollectItem item : mItems) {
            if(CommonUtils.checkDataIsTure(item.isBookmarked)) {
                target.put("archiveIds["+index+++"]",item.id);
            }
        }

        return target;
    }



    class UserCollectViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_ch_box)
        CheckBox mCheckBox;

        @Bind(R.id.id_tv_collect_title)
        TextView mTvTitle;

        @Bind(R.id.id_tv_sub_title)
        TextView mTvSubTitle;

        @Bind(R.id.id_tv_private)
        TextView mTvPrivate;

        public UserCollectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
