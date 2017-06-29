package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ActionDataEntity;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 21:51 <p>
 * interface :
 */

public class ActionRecyclerAdapter extends BaseRecyclerAdapter<ActionDataEntity.Item> {

    public ActionRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ActionVieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.action_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder,final ActionDataEntity.Item item, int position) {
        ActionVieHolder actionHolder = (ActionVieHolder) holder;

        ImageUtils.showUrlImage(item.trigger.thumbnailUrl,actionHolder.mTagIcon);
        actionHolder.mTagName.setText(item.trigger.name);
        actionHolder.mTagDesc.setText("标签的热门" + convertEnglish2String(item.rootObjectType));
        actionHolder.mTagTime.setText(item.date);
        actionHolder.mTagTitle.setText(item.title);

        actionHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri localUri = Uri.parse(item.url);
                Intent _intent = new Intent(mContext,SchemeActivity.class);
                _intent.setData(localUri);
                _intent.putExtra("inner",true);
                mContext.startActivity(_intent);
            }
        });
    }


    private String convertEnglish2String(String rootObjectType) {
        switch (rootObjectType){
            default:
            case "question":
                return "提问";
            case "article":
                return "文章";
            case "answer":
                return "回答" ;
        }
    }

    class ActionVieHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_tag)
        ImageView mTagIcon;

        @Bind(R.id.id_tv_tag_name)
        TextView mTagName;

        @Bind(R.id.id_tv_tag_desc)
        TextView mTagDesc;

        @Bind(R.id.id_tv_tag_time)
        TextView mTagTime;

        @Bind(R.id.id_tv_tag_title)
        TextView mTagTitle;

        public ActionVieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
