package com.micro.mysegmentdefault.ui.write;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加具体内容 TODO 富文本编辑器应该这个版本不加上去了 东西太多了
 * 问题内容界面
 */
public class AskContentActivity extends BaseActivity {

    @Bind(R.id.id_et_content)
    EditText mEtContent;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ask_content;
    }

    @Override
    protected void initViews() {
        String content = getIntent().getStringExtra("content");
        if(!TextUtils.isEmpty(content)) {
            mEtContent.setText(content);
        }
    }

    @OnClick(R.id.id_iv_right)
    public void onSubmit(View v) {
        String content = mEtContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_content_empty_error);
            return;
        }

        if(content.length() < 10) {
            showToast(R.string.str_content_length_short_error);
            return;
        }

        setResult(RESULT_OK,new Intent().putExtra("content",content));
        finish();
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        this.finish();
    }



}
