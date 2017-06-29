package com.micro.mysegmentdefault.ui.setting;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.PushMessageDataEntity;
import com.micro.mysegmentdefault.middle.MessagePushContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.MessagePushModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.MessagePushPresenter;
import com.micro.mysegmentdefault.view.widget.MessagePushItemLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * author : micro_hx <p>
 * desc : 消息推送设置<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 13:15 <p>
 * interface :
 */

public class MessagePushSettingActivity
        extends BaseActivity<MessagePushPresenter,MessagePushModel>
        implements MessagePushContract.AbsMessagePushView,
                   MessagePushItemLayout.OnCheckBoxOnSelectListener,
                   CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.id_new_attention)
    MessagePushItemLayout mAttentionNewQuestion;

    @Bind(R.id.id_attention_me)
    MessagePushItemLayout mAttentionMe;

    @Bind(R.id.id_vote_me)
    MessagePushItemLayout mVotedMe;

    @Bind(R.id.id_private_message)
    MessagePushItemLayout mPrivateMessage;

    @Bind(R.id.id_mention_me)
    MessagePushItemLayout mMentionMe;

    @Bind(R.id.id_comment_me)
    MessagePushItemLayout mCommentMe;

    @Bind(R.id.id_invite_me_answer)
    MessagePushItemLayout mInviteMeAnswer;

    @Bind(R.id.id_switch)
    Switch mDisturbSwitch;

    private boolean mIsFirstTimeEnter = true;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.getMessagePushSetting();
    }

    @Override
    protected void initViews() {
        mAttentionNewQuestion.setSelectListener(this);
        mAttentionMe.setSelectListener(this);
        mVotedMe.setSelectListener(this);
        mPrivateMessage.setSelectListener(this);
        mMentionMe.setSelectListener(this);
        mInviteMeAnswer.setSelectListener(this);
        mCommentMe.setSelectListener(this);

        mDisturbSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_message_push_setting;
    }

    @Override
    public void showMessagePushItem(PushMessageDataEntity data) {
        mAttentionNewQuestion.setCheckBox(data.data.api_answer);
        mAttentionMe.setCheckBox(data.data.api_follow);
        mVotedMe.setCheckBox(data.data.api_vote);
        mPrivateMessage.setCheckBox(data.data.api_message);
        mMentionMe.setCheckBox(data.data.api_mention);
        mInviteMeAnswer.setCheckBox(data.data.api_invite);
        mCommentMe.setCheckBox(data.data.api_comment);
        mDisturbSwitch.setChecked(data.data.api_disturb == 1);

        mIsFirstTimeEnter= false;
    }

    @Override
    public void onRequestDataError() {
        showToast(R.string.str_operation_error);
    }

    @Override
    public void onMessagePushSettingResult(boolean result) {
        if(!result) {
            showToast(R.string.str_operation_error);
        }
    }

    @Override
    public void onSelect(View view) {
        initParamsMap();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        initParamsMap();
    }

    private Map<String,String> mParamsMap = new HashMap<>();
    private void initParamsMap() {
        if(mIsFirstTimeEnter) return;

        mParamsMap.clear();
        mParamsMap.put("allows[api_comment]" , mCommentMe.getResult());
        mParamsMap.put("allows[api_answer]" , mAttentionNewQuestion.getResult());
        mParamsMap.put("allows[api_follow]" , mAttentionMe.getResult());
        mParamsMap.put("allows[api_invite]" , mInviteMeAnswer.getResult());
        mParamsMap.put("allows[api_vote]" , mVotedMe.getResult());
        mParamsMap.put("allows[api_message]" , mPrivateMessage.getResult());
        mParamsMap.put("allows[api_mention]" , mMentionMe.getResult());
        mParamsMap.put("allows[api_disturb]" , mDisturbSwitch.isChecked()? "1":"0");
        mPresenter.messagePushSetting(mParamsMap);
    }

}
