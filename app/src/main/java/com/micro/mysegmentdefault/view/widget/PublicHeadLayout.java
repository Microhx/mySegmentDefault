package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 21:49 <p>
 * interface :
 */

public class PublicHeadLayout extends RelativeLayout {

    public ImageButton mIvBack;
    public TextView mTvTitle;
    public ImageButton mIvRightBtn;
    public TextView mTvRight;

    public PublicHeadLayout(Context context) {
        this(context, null);
    }

    public PublicHeadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PublicHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PublicHeadLayout);
        int title = a.getResourceId(R.styleable.PublicHeadLayout_pub_title, -1);
        if (title > 0) {
            mTvTitle.setText(title);
        }

        boolean rightIvVisible = a.getBoolean(R.styleable.PublicHeadLayout_pub_ri_visible, false);
        mIvRightBtn.setVisibility(rightIvVisible ? VISIBLE : GONE);
        mTvRight.setVisibility(rightIvVisible ? GONE : VISIBLE);


        int rightResource = a.getResourceId(R.styleable.PublicHeadLayout_pub_ri_resource, -1);
        if (rightResource > 0) {
            mIvRightBtn.setImageResource(rightResource);
        }

        int rightTv = a.getResourceId(R.styleable.PublicHeadLayout_pub_tv_txt, -1);
        if (rightTv > 0) {
            mTvRight.setText(rightTv);
        }

        a.recycle();
    }

    public void setTitle(int title) {
        mTvTitle.setText(title);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }


    private void initViews() {
        View rootView = View.inflate(getContext(), R.layout.base_head_layout, this);
        mIvBack = (ImageButton) rootView.findViewById(R.id.id_iv_back);
        mTvTitle = (TextView) rootView.findViewById(R.id.id_tag_title);
        mIvRightBtn = (ImageButton) rootView.findViewById(R.id.id_iv_right);
        mTvRight = (TextView) rootView.findViewById(R.id.id_tv_right);
    }

    public void setRightSource(int res) {
        mIvRightBtn.setVisibility(VISIBLE);
        mIvRightBtn.setImageResource(res);
    }
}
