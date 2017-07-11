package com.micro.mysegmentdefault.middleimpl.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/11 - 13:30 <p>
 * interface :
 */

public class RegisterNextStepFragment extends DialogFragment implements View.OnClickListener {

    TextInputLayout mInputPhone;

    TextInputEditText mEtPhone;

    TextInputLayout mInputCode;

    TextInputEditText mEtCode;

    TextView  mTvCode;

    TextView mTvFinish;

    private String mPhoneNumber;

    private CountDownTimer mCountDownTimer;

    private OnCheckPhoneListener mCheckPhoneListener ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null != getArguments()){
            mPhoneNumber = getArguments().getString("phone","");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = View.inflate(getActivity(), R.layout.fragment_register_layout,null);

        mInputPhone = (TextInputLayout) rootView.findViewById(R.id.id_input_phone);
        mEtPhone = (TextInputEditText) rootView.findViewById(R.id.id_et_phone);
        mInputCode = (TextInputLayout) rootView.findViewById(R.id.id_input_code);
        mEtCode = (TextInputEditText) rootView.findViewById(R.id.id_et_code);
        mTvCode = (TextView) rootView.findViewById(R.id.id_tv_send_code);
        mTvFinish = (TextView) rootView.findViewById(R.id.id_tv_finish);

        mEtPhone.setText(mPhoneNumber);
        mEtPhone.setSelection(mPhoneNumber.length());

        mTvCode.setOnClickListener(this);
        mTvFinish.setOnClickListener(this);

        return builder.
               setView(rootView).
               setTitle(R.string.str_check_phone).
               create();
    }

    public  void startCount(){
        mCountDownTimer = new CountDownTimer(60 * 1000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvCode.setEnabled(false);
                mTvCode.setTextColor(Color.parseColor("#010001"));
                mTvCode.setText(String.format(getString(R.string.time_finish),millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                mTvCode.setEnabled(true);
                mTvCode.setTextColor(Color.parseColor("#039A63"));
                mTvCode.setText(R.string.str_re_send_code);
            }
        };

        mCountDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        if(v == mTvCode) {
            startCount();
        }else if(v == mTvFinish) {
            String code = mEtCode.getText().toString();
            if(TextUtils.isEmpty(code)){
                mInputCode.setError(getString(R.string.str_code_is_null));
                return;
            }

            if(null != mCheckPhoneListener) mCheckPhoneListener.onCheckUserCode(code);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCountDownTimer.cancel();
    }

    public void showErrorLog(String message) {
        if(null != mInputCode){
            mInputCode.setError(message);
        }
    }

    public interface OnCheckPhoneListener {
        void onCheckUserCode(String code);
    }

    public void setOnCheckPhoneListener(OnCheckPhoneListener listener) {
        this.mCheckPhoneListener = listener;
    }


}
