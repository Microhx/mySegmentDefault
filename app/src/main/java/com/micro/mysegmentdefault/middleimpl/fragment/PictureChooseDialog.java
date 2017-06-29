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
 * desc : 头像选择fragment <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 13:20 <p>
 * interface :
 */

public class PictureChooseDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private PictureChooseInterface mInterface;

    public void setInterface(PictureChooseInterface mInterface){
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(getResources().getStringArray(R.array.picture_choose),this);
        builder.setTitle(R.string.str_choose_picture);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(null != mInterface) {
            mInterface.onPictureChoose(which);
        }
    }

    public interface PictureChooseInterface {
        void onPictureChoose(int position);
    }

}
