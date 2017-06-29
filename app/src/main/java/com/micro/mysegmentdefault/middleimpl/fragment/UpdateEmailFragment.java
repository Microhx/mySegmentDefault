package com.micro.mysegmentdefault.middleimpl.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.regex.Pattern;

/**
 * author : micro_hx <p>
 * desc : 修改邮箱地址的fragment Dialog<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 16:53 <p>
 * interface :
 */

public class UpdateEmailFragment extends DialogFragment {


    private TextInputLayout mInputLayout;

    private EditText mEditInput ;

    private OnEmailFinishListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = View.inflate(getActivity(),R.layout.fragment_send_email_layout,null);
        mInputLayout = (TextInputLayout) rootView.findViewById(R.id.id_input_layout);
        mEditInput = (EditText) rootView.findViewById(R.id.id_et_content);
        mInputLayout.setHint(getString(R.string.str_email));

        rootView.findViewById(R.id.id_tv_sure).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        builder.setTitle(getString(R.string.str_update_eamil)).
                setView(rootView).
                setCancelable(false);
        return builder.create();
    }

    private void getData() {
        String email = mEditInput.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            mInputLayout.setError(getString(R.string.str_email_should_not_null));
            return;
        }

        if(!Pattern.matches("[\\w_$]+@[\\w_$.]+" , email)){
            mInputLayout.setError(getString(R.string.str_email_format_error));
            return;
        }

        if(null != mListener) mListener.onFinish(email);

        dismiss();
    }

    public void setListener(OnEmailFinishListener mListener) {
        this.mListener = mListener;
    }

    public interface OnEmailFinishListener{
        void onFinish(String email); //
    }

}




