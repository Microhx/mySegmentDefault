package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.ui.QuestionDetailActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 17:12 <p>
 * interface :
 */

public class TagDetailQuestionRecyclerAdapter extends BaseRecyclerAdapter<TagDetailQuestionEntity.Item> {

    public static final int GREEN_BACKGROUND_COLOR = Color.parseColor("#EFF9F6");
    public static final int GRAY_BACKGROUND_COLOR = Color.parseColor("#C8C7C8");

    public static final int GREEN_TEXT_COLOR = Color.parseColor("#0C9F69");
    public static final int GRAY_TEXT_COLOR = Color.parseColor("#A8A7A8");

    private int mDefaultPadding ;

    public TagDetailQuestionRecyclerAdapter(Context ctx) {
        super(ctx);
        mDefaultPadding = CommonUtils.dip2px(SegmentApplication.getApplication(),6);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new QuestionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_question_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, TagDetailQuestionEntity.Item item, int position) {
        QuestionViewHolder questionHolder = (QuestionViewHolder) holder;
        questionHolder.mTvTitle.setText(item.title);
        questionHolder.mTvPublishTime.setText(item.createdDate);

        if (!item.isClosed) {
            questionHolder.mTvAnswer.setText(item.answers + "\n回答");
            questionHolder.mTvAnswer.setBackgroundColor(GREEN_BACKGROUND_COLOR);
            questionHolder.mTvAnswer.setTextColor(GREEN_TEXT_COLOR);
        } else {
            questionHolder.mTvAnswer.setTextColor(GRAY_TEXT_COLOR);
            questionHolder.mTvAnswer.setBackgroundColor(GRAY_BACKGROUND_COLOR);
            questionHolder.mTvAnswer.setText(item.answers + "\n解决");
        }

        addTagItems(questionHolder,item);
        addTagItemsListeners(questionHolder,item);
    }

    private void addTagItemsListeners(QuestionViewHolder questionHolder, final TagDetailQuestionEntity.Item  item) {
        questionHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDetailActivity.start(item.id,QuestionDetailActivity.class);
            }
        });
    }

    private void addTagItems(QuestionViewHolder questionHolder, TagDetailQuestionEntity.Item item) {
        questionHolder.mTagLayout.removeAllViews();

        if(!CommonUtils.collectionIsNull(item.tags)) {
            for(TagDetailQuestionEntity.Tag tag : item.tags) {
                TextView tv = new TextView(SegmentApplication.getApplication());
                tv.setBackgroundColor(GREEN_BACKGROUND_COLOR);
                tv.setText(tag.name);
                tv.setTextColor(GREEN_TEXT_COLOR);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
                tv.setPadding(mDefaultPadding,mDefaultPadding,mDefaultPadding,mDefaultPadding);
                questionHolder.mTagLayout.addView(tv,getDefaultParams());
            }
        }
    }

    private LinearLayout.LayoutParams mLayoutParams ;
    private LinearLayout.LayoutParams getDefaultParams() {
        if(null == mLayoutParams) {
            mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParams.setMargins(0,0,mDefaultPadding,0);
        }
        return mLayoutParams;
    }


    class QuestionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_title)
        TextView mTvTitle;

        @Bind(R.id.id_tv_answer)
        TextView mTvAnswer;

        @Bind(R.id.id_tag_layout)
        LinearLayout mTagLayout;

        @Bind(R.id.id_tv_publish_time)
        TextView mTvPublishTime;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
