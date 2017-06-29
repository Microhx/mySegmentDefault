package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : 消息推送子条目ItemLayout<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 13:21 <p>
 * interface :
 */

public class MessagePushItemLayout extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckBox;
    private TextView mTextView;
    private View mBottomLine;
    private Switch mSwitch;

    private OnCheckBoxOnSelectListener mSelectListener;

    public MessagePushItemLayout(Context context) {
        this(context, null);
    }

    public MessagePushItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MessagePushItemLayout);
        boolean isCheck = a.getBoolean(R.styleable.MessagePushItemLayout_push_checked, false);
        int messageContent = a.getResourceId(R.styleable.MessagePushItemLayout_push_content, R.string.str_null);
        boolean showBottomLine = a.getBoolean(R.styleable.MessagePushItemLayout_push_bottom_line, true);
        boolean showSwitch = a.getBoolean(R.styleable.MessagePushItemLayout_push_show_switch, false);
        int tag = a.getResourceId(R.styleable.MessagePushItemLayout_push_tag, R.string.str_null);

        mCheckBox.setChecked(isCheck);
        mBottomLine.setVisibility(showBottomLine ? VISIBLE : GONE);
        mCheckBox.setOnCheckedChangeListener(this);
        mCheckBox.setVisibility(showSwitch ? GONE : VISIBLE);
        mSwitch.setVisibility(showSwitch ? VISIBLE : GONE);
        mSwitch.setOnCheckedChangeListener(this);

        if(messageContent > 0) {
            mTextView.setText(messageContent);
        }

        if(tag > 0) {
            mSwitch.setTag(context.getString(tag));
        }

        a.recycle();
    }

    public MessagePushItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.msg_item_push_layout, this, false);
        mCheckBox = (CheckBox) rootView.findViewById(R.id.id_check_box);
        mTextView = (TextView) rootView.findViewById(R.id.id_tv_content);
        mSwitch = (Switch) rootView.findViewById(R.id.id_switch);
        mBottomLine = rootView.findViewById(R.id.id_bottom_line);

        addView(rootView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setCheckBox(int check) {
        mCheckBox.setChecked(check == 1);
    }

    public void  setSwitchOpen(boolean isBind) {
        mSwitch.setChecked(isBind);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (null != mSelectListener) {
            mSelectListener.onSelect(buttonView);
        }
    }

    //listener
    public void setSelectListener(OnCheckBoxOnSelectListener mSelectListener) {
        this.mSelectListener = mSelectListener;
    }

    public String getResult() {
        return mCheckBox.isChecked() ? "1" : "0";
    }

    public boolean isClose() {
        return mSwitch.isChecked();
    }


    public interface OnCheckBoxOnSelectListener {
        void onSelect(View view);
    }

}
