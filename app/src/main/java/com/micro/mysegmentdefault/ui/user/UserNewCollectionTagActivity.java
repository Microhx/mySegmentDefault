package com.micro.mysegmentdefault.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.middle.UserAddNewCollectContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserAddNewCollectModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAddNewCollectPresenter;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * author : micro_hx <p>
 * desc : 添加新的收藏标签<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 11:56 <p>
 * interface :
 */

public class UserNewCollectionTagActivity
                extends BaseActivity<UserAddNewCollectPresenter, UserAddNewCollectModel>
                        implements UserAddNewCollectContract.AbsNewCollectView {

    //收藏夹title最大长度
    public static final int MAX_COLLECT_LENGTH = 10;

    @Bind(R.id.id_widget_pb_layout)
    PublicHeadLayout mPublicLayout;

    @Bind(R.id.id_et_collect_name)
    EditText mEtTitle;

    @Bind(R.id.id_et_desc)
    EditText mEtDesc;

    @Bind(R.id.id_switch)
    Switch mSwitch;

    //是否设为私密
    private boolean mIsPrivate = false;

    //收藏夹的Id
    private String mCollectionId ;

    private boolean isCollectionUpdate = false;

    /**
     * 更新Tag
     * @param title
     * @param description
     * @param id
     * @param isPrivate
     */
    public static void start(Activity act ,String title , String description , String id , boolean isPrivate , int requestCode) {
        Intent _intent = new Intent(act,UserNewCollectionTagActivity.class).
                putExtra("title",title).
                putExtra("description",description).
                putExtra("id",id).
                putExtra("isPrivate",isPrivate).
                putExtra("isUpdate",true);
        act.startActivityForResult(_intent,requestCode);
    }


    @Override
    protected void initViews() {
        if(getIntent().getBooleanExtra("isUpdate",false)) {
            mPublicLayout.setTitle(R.string.str_update_collection_favorite);

            mEtTitle.setText(getIntent().getStringExtra("title"));
            mEtDesc.setText(getIntent().getStringExtra("description"));
            mSwitch.setChecked(getIntent().getBooleanExtra("isPrivate",false));
            mCollectionId = getIntent().getStringExtra("id");
            isCollectionUpdate = true ;

            mEtTitle.setSelection(mEtTitle.length());
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnTextChanged(R.id.id_et_collect_name)
    public void onTextChange(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(s) && s.length() > MAX_COLLECT_LENGTH) {
            mEtTitle.setText(s.toString().substring(0, MAX_COLLECT_LENGTH));
            mEtTitle.setSelection(MAX_COLLECT_LENGTH);
        }
    }


    @OnCheckedChanged(R.id.id_switch)
    public void onSwitchCheck(CompoundButton buttonView, boolean isChecked) {
        mIsPrivate = isChecked;
    }

    @OnClick(R.id.id_iv_back)
    public void onBack(View v) {
        finish();
    }

    @OnClick(R.id.id_tv_right)
    public void addNewCollection(View w) {
        String title = mEtTitle.getText().toString().trim();
        String desc = mEtDesc.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            showToast("必须填写文件夹名");
            return;
        }

        if(isCollectionUpdate) {
            mPresenter.updateCollect(mCollectionId,title,desc,mIsPrivate);
        }else{
            mPresenter.addNewCollect(title, desc, mIsPrivate);
        }

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_new_collection;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addNewCollectSuccess(String id, String title, String isPrivate) {
        Intent _intent = new Intent();
        _intent.putExtra("id", id).
                putExtra("title", title).
                putExtra("isPrivate", isPrivate);

        setResult(RESULT_OK,_intent);
        finish();
    }

    @Override
    public void addNewCollectError() {
        showToast("新建文件失败，请稍后重试！");
    }

    @Override
    public void updateCollectResult(BaseDataEntity entity) {
        if(null != entity) {
            if(entity.status == 0) {
                showToast(R.string.str_operation_success);

                setResult(RESULT_OK);
                finish();
            }else {
                showToast(entity.message);
            }
        }else {
            showToast(R.string.str_operation_error);
        }
    }
}
