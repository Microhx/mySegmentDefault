package com.micro.mysegmentdefault.ui.write;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户提问界面
 */
public class AskQuestionActivity extends BaseActivity {

    @Bind(R.id.id_et_title)
    EditText mEtTitle ;

    @Bind(R.id.id_et_tag)
    EditText mEtTag ;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_ask_question ;
    }

    @OnClick(R.id.id_et_title)
    public void onChooseTitle(View v) {
            goForResult(AskTitleActivity.class,100);
    }

    @OnClick(R.id.id_et_tag)
    public void onChooseTag(View v) {
         goForResult(AskTagActivity.class,200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK) {
            mEtTitle.setText(data.getStringExtra("title"));
        }
    }
}
