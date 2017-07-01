package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseListRecyclerAdapter;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/30 - 22:54 <p>
 * interface :
 */

public class SelectSameQuestionAdapter extends BaseListRecyclerAdapter<SearchDataEntity.SearchItem> {

    public SelectSameQuestionAdapter(Context ctx) {
        super(ctx, R.layout.tag_question_item_layout);
    }

    @Override
    protected void convertData(ViewHolderHelper holder, SearchDataEntity.SearchItem item, int position) {
        holder.setTextView(R.id.id_tv_title,item.excerpt);
        holder.setAcceptText(R.id.id_tv_answer,item.answers, CommonUtils.safeParseBoolean(item.isAccepted));
        holder.setViewGone(R.id.id_tv_publish_time,true);
        holder.setViewGone(R.id.id_tag_layout,true);
    }
}
