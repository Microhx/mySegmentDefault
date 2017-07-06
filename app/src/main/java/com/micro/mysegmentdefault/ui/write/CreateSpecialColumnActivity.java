package com.micro.mysegmentdefault.ui.write;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.CreateSpecialColumnContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.CreateSpecialColumnModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.CreateSpecialColumnPresenter;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 创建新的专栏
 */
public class CreateSpecialColumnActivity extends BaseActivity<CreateSpecialColumnPresenter,CreateSpecialColumnModel> implements CreateSpecialColumnContract.AbsCreateSpecialColumnView {

    @Bind(R.id.id_title)
    TextInputEditText mTitle;

     @Bind(R.id.id_website)
    TextInputEditText mWebSite ;

    @Bind(R.id.id_et_detail)
    EditText mEtContent;

    @Bind(R.id.id_input_title)
    TextInputLayout mTitleInput ;

    @Bind(R.id.id_input_website)
    TextInputLayout mWebSetInput;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_create_special_cloumn;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        this.finish();
    }

    @OnClick(R.id.id_tv_right)
    public void onCommit(View v){
        String title = mTitle.getText().toString().trim();
        String website = mWebSite.getText().toString().trim();
        String content = mEtContent.getText().toString().trim();

        if(TextUtils.isEmpty(title)){
            mTitleInput.setError(getString(R.string.str_title_not_allow_null));
            return;
        }

        if(TextUtils.isEmpty(website)){
            mWebSetInput.setError(getString(R.string.set_website_not_allow_null));
            return;
        }

        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_content_not_allow_null);
            return ;
        }
        mPresenter.commitUserSpecialColumn(title,website,content);
    }


    @Override
    public void showCommitResult(OnlyData data) {
        if(null != data) {
            if(data.status == 1) {
                showToast(data.message);
            }else{
                showToast(R.string.str_operation_success);
            }
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
