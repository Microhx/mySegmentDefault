package com.micro.mysegmentdefault.ui.write;

import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.AskQuestionContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.AskQuestionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.AskQuestionPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.view.widget.TagDynamicDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.http.PUT;

/**
 * 用户提问界面
 */
public class AskQuestionActivity extends BaseActivity<AskQuestionPresenter,AskQuestionModel> implements AskQuestionContract.AbsAskQuestionView {

    @Bind(R.id.id_et_title)
    EditText mEtTitle;

    @Bind(R.id.id_et_tag)
    EditText mEtTag;

    @Bind(R.id.id_ask_question_desc)
    TextView mTvContent;

    private ArrayList<BestTag> mBestTags;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ask_question;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @OnClick(R.id.id_et_title)
    public void onChooseTitle(View v) {
        String title = mEtTitle.getText().toString().trim();

        goForResult(new Intent(this,AskTitleActivity.class).putExtra("title",title), 100);
    }

    @OnClick(R.id.id_et_tag)
    public void onChooseTag(View v) {
        goForResult(new Intent(this,AskTagActivity.class).putExtra("bestTags",mBestTags),200);
    }

    @OnClick(R.id.id_ask_question_desc)
    public void onAddContent(View v) {
        String content = mTvContent.getText().toString();
        goForResult(new Intent(this,AskContentActivity.class).putExtra("content",content),300);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            mEtTitle.setText(data.getStringExtra("title"));

        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            mBestTags = data.getParcelableArrayListExtra("bestTags");
            resetTagList();
        } else if( requestCode == 300 && resultCode == RESULT_OK) {
            String content = data.getStringExtra("content");
            mTvContent.setText(content);
        }
    }

    private void resetTagList() {
        if (CommonUtils.collectionIsNull(mBestTags)) return;
        SpannableStringBuilder sb = new SpannableStringBuilder();
        int index = 0;
        for (BestTag tag : mBestTags) {
            String name = tag.name;
            int length = name.length();

            sb.append(name);
            sb.setSpan(new TagDynamicDrawable(tag), index, index + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            index += length;
        }
        mEtTag.getText().clear();
        mEtTag.getText().append(sb);
    }

    @OnClick(R.id.id_iv_right)
    public void onCommit(View v) {
        String title = mEtTitle.getText().toString().trim();
        String content = mTvContent.getText().toString().trim();

        if(TextUtils.isEmpty(title)) {
            showToast(R.string.str_add_ask_title);
            return;
        }

        if(CommonUtils.collectionIsNull(mBestTags)) {
            showToast(R.string.str_add_ask_tag);
            return;
        }

        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_content_empty_error);
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("token", UserLogic.getUserToken());
        map.put("text",content);
        map.put("title",title);
        int index = 0;
        for(BestTag tag : mBestTags){
            map.put("tags[" + index++ + "]" , tag.id);
        }

        mPresenter.addUserQuestion(map);
    }

    @Override
    public void showResult(OnlyData data) {
        if(null != data) {
            showToast(data.message);
            finish();
        }else {
            showToast(R.string.str_operation_error);
        }
    }
}
