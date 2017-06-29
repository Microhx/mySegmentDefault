package com.micro.mysegmentdefault.ui.user.attention;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;

import butterknife.OnClick;

/**
 * 用户关注界面 :)最简单的界面了吧
 */
public class UserAttentionActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_attention;
    }

    @OnClick({R.id.id_tv_attention_person ,R.id.id_tv_attention_category ,
            R.id.id_tv_attention_collect , R.id.id_tv_attention_question ,
            R.id.id_tv_attention_tag})
    public void attention(View v) {
        switch (v.getId()) {
            case R.id.id_tv_attention_person:
                go(UserAttentionPersonActivity.class);
                break;

            case R.id.id_tv_attention_tag:
                go(UserAttentionTagActivity.class);
                break;

            case R.id.id_tv_attention_question:
                go(UserAttentionQuestionActivity.class);
                break;

        }






    }

}
