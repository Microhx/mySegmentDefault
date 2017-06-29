package com.micro.mysegmentdefault.ui.setting;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.UpdatePasswordEntity;
import com.micro.mysegmentdefault.middle.UpdatePasswordContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UpdatePasswordModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UpdatePasswordPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 更新用户密码
 */
public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordPresenter,UpdatePasswordModel> implements UpdatePasswordContract.AbsUpdatePasswordView {

    @Bind(R.id.id_et_old_pass)
    EditText mETOldPass ;

    @Bind(R.id.id_et_new_pass)
    EditText mETNewPass ;

    @Bind(R.id.id_et_new_pass_again)
    EditText mEtNewPassAgain;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_update_password;
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        this.finish();
    }

    @OnClick(R.id.id_iv_right)
    public void onSubmitPassword(View v) {
        String oldPass = mETOldPass.getText().toString().trim();
        String newPass = mETNewPass.getText().toString().trim();
        String newPassAgain = mEtNewPassAgain.getText().toString().trim();

        if(TextUtils.isEmpty(oldPass)) {
            showToast(R.string.str_input_pass);
            return;
        }

        if(TextUtils.isEmpty(newPass) || TextUtils.isEmpty(newPassAgain)) {
            showToast(R.string.str_input_new_pass);
            return;
        }

        if(!newPass.equals(newPassAgain)) {
            showToast(R.string.str_pass_twice_differ);
            return;
        }
        mPresenter.updatePassword(oldPass,newPass,newPassAgain);
    }


    @Override
    public void onUpdatePasswordResult(UpdatePasswordEntity entity) {
        if(null == entity) {
            showToast(R.string.str_update_password_error);
            return;
        }

        if(entity.status == 1){
            showToast(entity.message);
            return;
        }

        if(entity.status == 0) {
            showToast(R.string.str_update_password_success);
            finish();
            return;
        }

        showToast(R.string.str_update_password_error);
    }
}
