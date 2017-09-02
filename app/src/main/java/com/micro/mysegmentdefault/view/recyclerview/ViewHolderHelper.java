package com.micro.mysegmentdefault.view.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;

import java.util.List;

/**
 * Created by Javaintalling on 2016/10/10.
 */
public class ViewHolderHelper extends RecyclerView.ViewHolder {
    private SparseArray<View> mContainer;
    private Context mCtx;
    private View mConvertView;
    private int mLayoutId;
    private int mPosition;

    public ViewHolderHelper(Context mCtx, View itemView, int mPosition) {
        super(itemView);
        this.mCtx = mCtx;
        mConvertView = itemView;
        mContainer = new SparseArray<>();
        this.mPosition = mPosition;
    }

    public static ViewHolderHelper get(Context context, View convertView,
                                       ViewGroup parent, int mLayoutId, int position) {

        ViewHolderHelper helper;
        if (null == convertView) {
            View itemView = LayoutInflater.from(context).inflate(mLayoutId, parent, false);
            helper = new ViewHolderHelper(context, itemView, position);
            helper.mLayoutId = mLayoutId;
        } else {
            helper = (ViewHolderHelper) convertView.getTag();
            helper.mPosition = position;
        }
        return helper;
    }

    public <T extends View> T getView(int id) {
        View target = mContainer.get(id);
        if (null == target) {
            target = mConvertView.findViewById(id);
            mContainer.put(id, target);
        }

        if (null == target) {
            throw new NullPointerException("view is null , the id is " + id);
        }

        return (T) target;
    }

    public ViewHolderHelper setTextView(int id, CharSequence msg) {
        TextView tv = getView(id);
        tv.setText(msg);
        return this;
    }


    public ViewHolderHelper setTextView(int id, String msg) {
        TextView tv = getView(id);
        tv.setText(msg);
        return this;
    }

    public ViewHolderHelper setTagLayout(int id , List<TagDetailQuestionEntity.Tag> items) {
        LinearLayout ll = getView(id);
        ll.removeAllViews();
        if(CommonUtils.collectionIsNull(items)) return this;

        for(TagDetailQuestionEntity.Tag tag : items) {
            TextView tv = new TextView(SegmentApplication.getApplication());
            tv.setBackgroundColor(Color.parseColor("#EFF9F6"));
            tv.setText(tag.name);
            tv.setTextColor(Color.parseColor("#0C9F69"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
            tv.setPadding(10,10,10,10);
            ll.addView(tv,getDefaultParams());
        }

        return this;
    }


    public ViewHolderHelper setTextView(int id, int msg) {
        TextView tv = getView(id);
        tv.setText(msg);
        return this;
    }

    public ViewHolderHelper setHtmlTextView(int id, String msg) {
        TextView tv = getView(id);
        tv.setText(Html.fromHtml(msg));
        return this;
    }

    public ViewHolderHelper setTextView(int id, Spannable spannable) {
        TextView tv = getView(id);
        tv.setText(spannable, TextView.BufferType.SPANNABLE);
        return this;
    }

    public ViewHolderHelper setImageView(int id , String url){
        ImageView iv = getView(id);
        ImageUtils.showUrlImage(url,iv);
        return this;

    }


    public ViewHolderHelper setImageViewDrawable(int id, Drawable drawable) {
        ImageView iv = getView(id);
        iv.setImageDrawable(drawable);
        return this;
    }


    public ViewHolderHelper setViewBackground(int id, int background) {
        View view = getView(id);
        view.setBackgroundResource(background);
        return this;
    }


    public ViewHolderHelper setViewBackgroundColor(int id, int color) {
        View view = getView(id);
        view.setBackgroundColor(color);
        return this;
    }


    public ViewHolderHelper setImageBitmap(int id, Bitmap bitmap) {
        ImageView iv = getView(id);
        iv.setImageBitmap(bitmap);
        return this;
    }


    public ViewHolderHelper setTextViewColor(int id, int color) {
        TextView tv = getView(id);
        tv.setTextColor(color);
        return this;
    }

    public ViewHolderHelper setImageViewDefault(int id , int defaultImg) { //default
        ImageView iv = getView(id) ;
        iv.setImageResource(defaultImg);
        return this ;
    }


    public ViewHolderHelper setViewVisiable(int id, boolean sholudVisiable) {
        getView(id).setVisibility(sholudVisiable ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public ViewHolderHelper setViewGone(int id, boolean shouldGone) {
        getView(id).setVisibility(shouldGone ? View.GONE : View.VISIBLE);
        return this;
    }


    public ViewHolderHelper setAcceptText(int id, String answers, boolean b) {
        TextView tv = getView(id);
        tv.setText(answers);
        tv.setTextColor(b ? Color.parseColor("#838283"):Color.parseColor("#039A63"));
        tv.setBackgroundColor(b?Color.parseColor("#EFEEF0") : Color.parseColor("#EFF9F6"));
        return this;
    }


    public ViewHolderHelper setViewOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    public ViewHolderHelper setViewOnClickListener(View.OnClickListener listener, int... d) {
        int len = d.length;
        for (int i = 0; i < len; i++) {
            getView(d[i]).setOnClickListener(listener);
        }
        return this;
    }


    public String getTextViewStr(int id) {
        TextView tv = getView(id);
        return tv.getText().toString().trim();
    }

    public ViewHolderHelper setItemViewOnClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }


    public void updatePosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getLayoutId() {
        return mLayoutId;
    }


    public View getContentView() {
        return mConvertView;
    }

    private LinearLayout.LayoutParams mLayoutParams ;
    private LinearLayout.LayoutParams getDefaultParams() {
        if(null == mLayoutParams) {
            mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParams.setMargins(0,0,10,0);
        }
        return mLayoutParams;
    }


}
