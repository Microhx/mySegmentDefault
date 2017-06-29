package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;

/**
 * author : micro_hx <p>
 * desc : 用户添加评论页面 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/31 - 9:29 <p>
 * interface :
 */

public class UserAddCommentActivity extends BaseActivity {

    @Bind(R.id.id_layout_head)
    PublicHeadLayout mTitle;

    @Bind(R.id.id_et_content)
    EditText mEditContent;

    public static void start(Context ctx , String newsId , String commentName) {
        Intent _intent = new Intent(ctx,UserAddCommentActivity.class);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _intent.putExtra("newsId",newsId);
        _intent.putExtra("commentName",commentName);
        ctx.startActivity(_intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_add_comment;
    }

    @Override
    protected void initViews() {
        String text = "@Micro";
        mEditContent.getText().insert(mEditContent.getSelectionStart(), text);
    }
}
