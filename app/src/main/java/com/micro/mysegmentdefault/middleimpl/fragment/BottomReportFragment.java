package com.micro.mysegmentdefault.middleimpl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/5 - 10:36 <p>
 * interface :
 */

public class BottomReportFragment extends BottomSheetDialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_report_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @OnClick({R.id.id_tv_one,
            R.id.id_tv_two,
            R.id.id_tv_three,
            R.id.id_tv_four,
            R.id.id_tv_five,
            R.id.id_tv_six})
    public void onCall(View v) {

        //API Application Programming Interface
        //TODO  may be next version to finish this task
        //TODO for some reasons I can't get the API

        ToastUtils.showMessage(getContext(),R.string.str_unsupport_operation);
        dismiss();
    }


}
