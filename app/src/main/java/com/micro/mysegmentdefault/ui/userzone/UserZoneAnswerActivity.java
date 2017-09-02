package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserZoneAnswerModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAttentionQuestionPresenter;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 用户空间 ----> TA的回答 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/2 - 19:14 <p>
 * interface :
 */

public class UserZoneAnswerActivity extends AbBaseAttentionActivity<UserAttentionQuestionPresenter,UserZoneAnswerModel,TagDetailQuestionEntity.Item>  {

    private String uid;

    public static void start(Context ctx, String uid) {
        ctx.startActivity(new Intent(ctx,UserZoneAnswerActivity.class).putExtra("uid",uid));
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_user_answer);
    }

    @Override
    protected String getDefaultChannel() {
        return uid;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.activity_user_zone_question_layout;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, TagDetailQuestionEntity.Item item, int position) {
        if(null == item ||null == item.question) return;

        holder.setTextView(R.id.id_tv_title,item.title);
        holder.setViewVisiable(R.id.id_tv_content,true).setTextView(R.id.id_tv_content,item.excerpt);
        holder.setTextView(R.id.id_tv_comment_count,item.question.followers + "人关注  " + item.question.bookmarks + "人收藏");

        if(item.isAccepted){
            holder.setViewBackgroundColor(R.id.id_tv_answer,GRAY_BACKGROUND_COLOR);
            holder.setTextViewColor(R.id.id_tv_answer,GRAY_TEXT_COLOR);
            holder.setTextView(R.id.id_tv_answer,item.question.answers+"\n解决");
        }else{
            holder.setViewBackgroundColor(R.id.id_tv_answer,GREEN_BACKGROUND_COLOR);
            holder.setTextViewColor(R.id.id_tv_answer,GREEN_TEXT_COLOR);
            holder.setTextView(R.id.id_tv_answer,item.question.answers+"\n回答");
        }
    }
}
