package com.micro.mysegmentdefault.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 标签详细内容
 */
public class TagInformationActivity extends BaseActivity {

    @Bind(R.id.id_pub_title)
    PublicHeadLayout mTitle ;

    @Bind(R.id.id_content)
    TextView mContent ;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_tag_information ;
    }

    public static void start(Context context, String title , String content ) {
        Intent _intent = new Intent(context,TagInformationActivity.class).
                         putExtra("title",title).
                         putExtra("content",content);
        context.startActivity(_intent);
    }

    @Override
    protected void initViews() {
        mTitle.setTitle(getIntent().getStringExtra("title"));
        mContent.setText(getIntent().getStringExtra("content"));
    }

    @OnClick(R.id.id_iv_back)
    public void onCall(View v) {
        finish();
    }

}
