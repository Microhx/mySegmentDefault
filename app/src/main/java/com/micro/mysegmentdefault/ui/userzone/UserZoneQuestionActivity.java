package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserZoneQuestionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAttentionQuestionPresenter;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : TA的提问<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/2 - 18:37 <p>
 * interface :
 */

public class UserZoneQuestionActivity extends
        AbBaseAttentionActivity<UserAttentionQuestionPresenter,UserZoneQuestionModel,TagDetailQuestionEntity.Item> {


    private String uid;

    public static void start(Context ctx,String uid) {
        ctx.startActivity(new Intent(ctx,UserZoneQuestionActivity.class).putExtra("uid",uid));
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_user_question);
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
        holder.setTextView(R.id.id_tv_title,item.title);
        holder.setTextView(R.id.id_tv_comment_count,item.followers + "人关注  " + item.bookmarks + "人收藏");

        if(item.isAccepted){
            holder.setViewBackgroundColor(R.id.id_tv_answer,GRAY_BACKGROUND_COLOR);
            holder.setTextViewColor(R.id.id_tv_answer,GRAY_TEXT_COLOR);
            holder.setTextView(R.id.id_tv_answer,item.answers+"\n解决");
        }else{
            holder.setViewBackgroundColor(R.id.id_tv_answer,GREEN_BACKGROUND_COLOR);
            holder.setTextViewColor(R.id.id_tv_answer,GREEN_TEXT_COLOR);
            holder.setTextView(R.id.id_tv_answer,item.answers+"\n回答");
        }

    }
}
