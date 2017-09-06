package com.micro.mysegmentdefault.middleimpl.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : 性别选择对话框 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 16:51 <p>
 * interface :
 */

public class SexChooseDialog extends DialogFragment implements DialogInterface.OnClickListener{

    private OnSexChooseInterface onSexChoose;

    private int mChooseType ;

    public void setSexChooseListener(OnSexChooseInterface onSexChoose){
        this.onSexChoose = onSexChoose;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mChooseType = getArguments().getInt("type",-1);
        if(mChooseType == -1) {
            builder.setSingleChoiceItems(getResources().getStringArray(R.array.sex_choose),getArguments().getInt("sex"),this);
            builder.setTitle(R.string.str_choose_sex);
        }else if (mChooseType == 1) {
            builder.setSingleChoiceItems(getResources().getStringArray(R.array.toutiao_type),getArguments().getInt("index"),this);
            builder.setTitle(R.string.str_choose_toutiao_type);
        }

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(null != onSexChoose) {
            onSexChoose.onSexChoose(which);
        }
        dismiss();
    }

    public interface OnSexChooseInterface{
        void onSexChoose(int position);

    }


}
