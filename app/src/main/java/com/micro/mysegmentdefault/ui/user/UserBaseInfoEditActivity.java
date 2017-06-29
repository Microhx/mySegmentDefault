package com.micro.mysegmentdefault.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户基本信息编辑 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 14:49 <p>
 * interface :
 */

public class UserBaseInfoEditActivity extends BaseActivity {

    //姓名
    public static final  int TYPE_NAME = 100;
    //网站
    public static final int TYPE_WEBSITE = 102 ;
    //自我介绍
    public static final int TYPE_INTRODUCE = 103 ;


    private int mCurrentType = 0;

    private String mCurrentInfo ;


    @Bind(R.id.id_widget_layout)
    PublicHeadLayout mHead;

    @Bind(R.id.id_input_layout)
    TextInputLayout mTextInputLayout;

    @Bind(R.id.id_et_content)
    EditText mEditContent;


    public static void start(Activity ctx , int type , String info) {
        Intent _intent = new Intent(ctx,UserBaseInfoEditActivity.class);
        _intent.putExtra("type",type);
        _intent.putExtra("info",info);
        ctx.startActivityForResult(_intent,type);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mCurrentType = getIntent().getIntExtra("type",TYPE_NAME);
        mCurrentInfo = getIntent().getStringExtra("info") ;
    }


    @Override
    protected void initViews() {
        switch (mCurrentType) {
            case TYPE_NAME:
                mHead.setTitle(R.string.str_user_name);
                mTextInputLayout.setHint(getString(R.string.str_user_name));
                setEditContent();


                break;

            case TYPE_WEBSITE:
                mHead.setTitle(R.string.str_user_website);
                mTextInputLayout.setHint(getString(R.string.str_user_website));
                setEditContent();
                break;

            case TYPE_INTRODUCE:
                mHead.setTitle(R.string.str_user_intro);
                mTextInputLayout.setHint(getString(R.string.str_user_intro));
                setEditContent();
                break;
        }
    }


    private void setEditContent() {
        if(!TextUtils.isEmpty(mCurrentInfo)) {
            mEditContent.setText(mCurrentInfo);
            mEditContent.setSelection(mCurrentInfo.length());
        }
    }

    @OnClick(R.id.id_iv_back)
    public void onBack(View v) {
        finish();
    }


    @OnClick(R.id.id_iv_right)
    public void onCall(View v) {
        String content = mEditContent.getText().toString().trim();
        if(mCurrentType == TYPE_NAME && TextUtils.isEmpty(content)) {
            showToast(R.string.str_name_must);
            return;
        }

        setResult(RESULT_OK,new Intent().putExtra("content",content));
        finish();
    }


    @Override
    protected int getContentViewId() {

        return R.layout.activity_user_base_info_edit;
    }
}
