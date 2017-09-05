package com.micro.mysegmentdefault.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.UserCommentResultDataEntity;
import com.micro.mysegmentdefault.middle.UserCommentContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCommentModelImpl;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserCommentPresenterImpl;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.DeleteableSpan;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户评论&回复 消息页面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 15:37 <p>
 * interface :
 */

//评论文章  https://api.segmentfault.com/news/1210000010752324/comments/add  text/token

//回复     https://api.segmentfault.com/comment/1050000010761581/reply      text/token

public class UserNewsCommentReplyActivity extends BaseActivity<UserCommentPresenterImpl,UserCommentModelImpl> implements UserCommentContract.UserCommentView {

    //回复者昵称
    private String mReplyName = "";

    //此次参与的Id
    private String mNewsId ;

    //是否为回复问题
    private boolean mIsReply ;

    //来源 0为文章评论 1为笔记评论
    private int mEnterType;

    @Bind(R.id.id_et_content)
    EditText mEtContent;

    public static void start(Activity ctx , String newsId , boolean isReply , String replyName,int requestCode) {
        start(ctx,newsId,isReply,replyName,0,requestCode);
    }

    public static void start(Activity ctx , String newsId , boolean isReply , String replyName, int enterType, int requestCode) {
        ctx.startActivityForResult(new Intent(ctx,UserNewsCommentReplyActivity.class).
                putExtra("newsId",newsId).
                putExtra("isReply",isReply).
                putExtra("enterType",enterType).
                putExtra("replyName",replyName),requestCode);
    }


    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        if(null != getIntent()) {
            mReplyName = getIntent().getStringExtra("replyName");
            mNewsId = getIntent().getStringExtra("newsId");
            mIsReply = getIntent().getBooleanExtra("isReply",false);
            mEnterType = getIntent().getIntExtra("enterType",0);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_news_comment_reply;
    }

    @Override
    protected void initViews() {
        initEditReplayName();
    }


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    private void initEditReplayName() {
        if(!TextUtils.isEmpty(mReplyName)) {
            mReplyName = String.format("@%s ",mReplyName);

            SpannableStringBuilder spannedBuilder = new SpannableStringBuilder(mReplyName);
            spannedBuilder.setSpan(new DeleteableSpan(),0,mReplyName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mEtContent.setText(spannedBuilder, TextView.BufferType.SPANNABLE);
            mEtContent.setSelection(mReplyName.length());
        }
    }

    @OnClick({R.id.id_iv_back,R.id.id_iv_right})
    public void onCall(View v) {
        switch (v.getId()) {
            case R.id.id_iv_back:
                    finish();
                break;


            case R.id.id_iv_right:
                commitComment();
                break;
        }
    }

    private void commitComment() {
        if(!checkUserLogin()) {
            return;
        }

        String content = mEtContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_error_input_content);
            return;
        }

        mPresenter.addUserComment(mEnterType,mNewsId,mIsReply,content);
    }

    @Override
    public void showCommentResult(UserCommentResultDataEntity entity) {
        if(null != entity) {
            if(entity.status != 0) {
                showToast(entity.message);
            }else {
                UserCommentResultDataEntity.Comment comment = entity.data.comment;

                Intent _intent =
                  new Intent().putExtra("createdDate",comment.createdDate).
                               putExtra("id" , comment.id).
                               putExtra("originalText", comment.originalText).
                               putExtra("avatarUrl",comment.user.avatarUrl).
                               putExtra("userName",comment.user.name).
                               putExtra("userUrl",comment.user.url);

                if(comment.replyUser != null) {
                    _intent.putExtra("replyName",comment.replyUser.name).
                            putExtra("replyUrl",comment.replyUser.url);
                }

                setResult(RESULT_OK,_intent);
                finish();
            }
        }else{
            showToast(R.string.str_operation_error);
        }
    }
}
