package com.micro.mysegmentdefault.middleimpl.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

/**
 * author : micro_hx <p>
 * desc : 修改邮箱地址的fragment Dialog<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 16:53 <p>
 * interface :
 */

public class CreateTagFragment extends DialogFragment {

    private TextInputLayout mInputLayout;

    private EditText mEditInput ;

    private TextView mSureText ;

    private OnEmailFinishListener mListener;

    private String mTagName ;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = View.inflate(getActivity(),R.layout.fragment_send_email_layout,null);
        mInputLayout = (TextInputLayout) rootView.findViewById(R.id.id_input_layout);
        mEditInput = (EditText) rootView.findViewById(R.id.id_et_content);
        mSureText = (TextView) rootView.findViewById(R.id.id_tv_sure);

        mInputLayout.setHint(getString(R.string.str_desc));
        mSureText.setText(R.string.str_commit);

        rootView.findViewById(R.id.id_tv_sure).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        builder.setTitle(getString(R.string.str_create_tag_desc)).
                setView(rootView).
                setCancelable(false);
        return builder.create();
    }

    private void getData() {
        String email = mEditInput.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            mInputLayout.setError(getString(R.string.str_tag_desc_should_not_null));
            return;
        }

        if(null != mListener) mListener.onFinish(mTagName,email);

        dismiss();
    }

    public void setListener(OnEmailFinishListener mListener) {
        this.mListener = mListener;
    }

    public void setTagName(String mTagName) {
        this.mTagName = mTagName;
    }

    public interface OnEmailFinishListener{
        void onFinish(String tagName ,String tagDesc); //
    }

}




