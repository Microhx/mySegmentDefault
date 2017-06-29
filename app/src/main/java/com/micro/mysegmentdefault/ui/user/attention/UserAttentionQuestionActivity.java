package com.micro.mysegmentdefault.ui.user.attention;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserAttentionQuestionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAttentionQuestionPresenter;
import com.micro.mysegmentdefault.ui.QuestionDetailActivity;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 用户关注的问题<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 13:28 <p>
 * interface :
 */

public class UserAttentionQuestionActivity  extends AbBaseAttentionActivity<UserAttentionQuestionPresenter,
                                                    UserAttentionQuestionModel,
                                                    TagDetailQuestionEntity.Item>{
    @Override
    protected void initViews() {
        super.initViews();
        mTvTitle.setText(R.string.str_user_attention_question);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.user_attention_question_item_layout;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final TagDetailQuestionEntity.Item item, int position) {
        holder.setTextView(R.id.id_tv_title,item.title);
        holder.setTextView(R.id.id_tv_info,item.followers+"人关注、" + item.answers + "人回答");
        holder.setTextView(R.id.id_tv_time,item.createdDate);
        holder.setTagLayout(R.id.id_layout_content,item.tags);
        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDetailActivity.start(item.id,QuestionDetailActivity.class);
            }
        }) ;
    }
}
