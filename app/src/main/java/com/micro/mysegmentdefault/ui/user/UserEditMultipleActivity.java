package com.micro.mysegmentdefault.ui.user;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户添加多个project 内容页面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/3 - 23:43 <p>
 * interface :
 */

public class UserEditMultipleActivity extends BaseActivity {

    public static final int TYPE_PROJECT = 201 ;
    public static final int TYPE_STUDY = 202 ;
    public static final int TYPE_WORK = 203 ;

    private int mCurrentType ;

    @Bind(R.id.id_widget_layout)
    PublicHeadLayout mHeadLayout;

    @Bind(R.id.id_et_title)
    EditText mEtTitle;

    @Bind(R.id.id_et_content)
    EditText mEtContent;

    @Bind(R.id.id_input_layout1)
    TextInputLayout mTitleLayout ;

    @Bind(R.id.id_input_layout2)
    TextInputLayout mContentLayout ;


    @Bind(R.id.id_btn_delete)
    Button mBtnDelete;

    private String mTitle;
    private String mContent;
    private boolean mIsDelete;
    private int mSort ;


    public static void start(Activity ctx , int type , String title , String content , boolean isDelete) {
        start(ctx,type,title,content,isDelete,-1);
    }

    public static void start(Activity ctx , int type , String title , String content , boolean isDelete,int sort) {
        Intent _intent = new Intent(ctx,UserEditMultipleActivity.class);
        _intent.putExtra("_type",type).
                putExtra("_title",title).
                putExtra("_content",content).
                putExtra("_isDelete",isDelete).
                putExtra("_sort" , sort);

        ctx.startActivityForResult(_intent,type);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mTitle = getIntent().getStringExtra("_title");
        mContent = getIntent().getStringExtra("_content");
        mCurrentType = getIntent().getIntExtra("_type",TYPE_PROJECT);
        mSort = getIntent().getIntExtra("_sort",-1);
        mIsDelete = getIntent().getBooleanExtra("_isDelete",false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_edit_multiple;
    }

    @Override
    protected void initViews() {
        initTitleAndHint();

        mEtTitle.setText(mTitle);
        mEtTitle.setSelection(TextUtils.isEmpty(mTitle)?0 : mTitle.length());

        mEtContent.setText(mContent);
        mEtContent.setSelection(TextUtils.isEmpty(mContent) ? 0 : mContent.length());

        if(mIsDelete) {
            mBtnDelete.setVisibility(View.VISIBLE);
        }
    }


    private void initTitleAndHint() {
        switch (mCurrentType) {
            case TYPE_PROJECT:
                mHeadLayout.setTitle(R.string.str_user_project);
                mTitleLayout.setHint(getString(R.string.str_add_project_and_article_shorter));
                mContentLayout.setHint(getString(R.string.str_add_introduce));
                break;


            case TYPE_STUDY:
                mHeadLayout.setTitle(R.string.str_user_study);
                mTitleLayout.setHint(getString(R.string.str_add_school));
                mContentLayout.setHint(getString(R.string.str_add_department));
                break;

            case TYPE_WORK:
                mHeadLayout.setTitle(R.string.str_user_work);
                mTitleLayout.setHint(getString(R.string.str_add_company_name));
                mContentLayout.setHint(getString(R.string.str_add_company_title));
                break;
        }
    }

    @OnClick(R.id.id_iv_back)
    public void onCallFinish(View v) {
        finish();
    }


    @OnClick(R.id.id_iv_right)
    public void onCommit(View v) {
        String title = mEtTitle.getText().toString().trim();
        String content = mEtContent.getText().toString().trim();

        if(TextUtils.isEmpty(title)){
            showToast(R.string.str_title_not_allow_null);
            return;
        }

        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_content_not_allow_null);
            return;
        }

        setResult(RESULT_OK, new Intent().
                             putExtra("_title",title).
                             putExtra("_content",content).
                             putExtra("_isUpdate" , mIsDelete).
                             putExtra("_sort",mSort));
        finish();
    }


    @OnClick(R.id.id_btn_delete)
    public void onDelete(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_confirm_to_delete);
        builder.setMessage("您确定删除" + getTargetConfirmMessage() + "吗?");
        builder.setPositiveButton(R.string.str_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                setResult(RESULT_OK, new Intent().putExtra("_isDelete" , true));
                finish();
            }
        });
        builder.setNegativeButton(R.string.str_cancel,null);
        builder.create().show();

    }

    private String getTargetConfirmMessage() {
        if(mCurrentType == TYPE_PROJECT) {
            return getString(R.string.str_user_project);
        }else if(mCurrentType == TYPE_STUDY) {
            return getString(R.string.str_user_study);
        }
            return getString(R.string.str_user_work);
    }


}
