package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.middle.UserNewNoteContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserNewNoteModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserNewNotePresenter;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户新增笔记页面 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/1 - 18:57 <p>
 * interface :
 */

public class UserNewNoteActivity extends BaseActivity<UserNewNotePresenter,UserNewNoteModel> implements DialogInterface.OnClickListener, UserNewNoteContract.AbsNewNoteView {

    @Bind(R.id.id_widget_pb_layout)
    PublicHeadLayout mPublicLayout;

    @Bind(R.id.id_input_layout)
    TextInputLayout mTextInputLayout;

    @Bind(R.id.id_et_collect_name)
    EditText mEtTitle;

    @Bind(R.id.id_et_desc)
    EditText mEtDesc;

    @Bind(R.id.id_switch)
    Switch mSwitch;

    @Bind(R.id.id_tv_choose_type)
    TextView mTvArticleType;

    private String[] mLanguageArray;

    private AlertDialog mAlertDialog;


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initViews() {
        mPublicLayout.mTvTitle.setText(R.string.str_note);
        mTextInputLayout.setHint("笔记描述");
        mEtDesc.setHint(R.string.str_note_content);

        mLanguageArray = getResources().getStringArray(R.array.language_note);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_new_collection;
    }

    @OnClick(R.id.id_tv_choose_type)
    public void onArticleTypeChoose(View v) {
        if (null == mAlertDialog) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_language_layout,
                                                             R.id.id_tv_content, mLanguageArray), this);
            mAlertDialog = alertDialogBuilder.create();
        }

        mAlertDialog.show();
    }

    @OnClick(R.id.id_tv_right)
    public void onCommit(View v){
        String _title = mEtTitle.getText().toString().trim();
        String _content = mEtDesc.getText().toString().trim();

        if(TextUtils.isEmpty(_title)){
            showToast(R.string.str_title_not_allow_null);
            return ;
        }

        if(TextUtils.isEmpty(_content)){
            showToast(R.string.str_content_not_allow_null);
            return;
        }

        mPresenter.addNewNote(_title, _content, getTextLanguage(mTvArticleType.getText().toString()),mSwitch.isChecked());
    }

    private String getTextLanguage(String text){
        if("纯文本".equals(text)) return "text";
        return text.toLowerCase();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        mTvArticleType.setText(mLanguageArray[which % mLanguageArray.length]);
    }

    @Override
    public void addNewNoteResult(boolean result) {
        if(result){
            showToast(R.string.str_note_publish_success);
            finish();
        }else{
            showToast(R.string.str_operation_error);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
