package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.ActiveTag;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.UserPageEntity;
import com.micro.mysegmentdefault.entity.UserRecordEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/20 - 13:20 <p>
 * interface :
 */

public class TagContainerLayout extends ViewGroup {

    //所有View 的容器
    //每一个List<View>代表每一行中View的容器
    private List<List<View>> mViewContainer = new ArrayList<>();

    //每一行的View容器
    private List<View> mEachLineContainer = new ArrayList<>();

    //行高的容器
    private List<Integer> mLineHeightList = new ArrayList<>();


    public TagContainerLayout(Context context) {
        this(context, null);
    }

    public TagContainerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagContainerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //实际目标值
        int lineWidth = 0;
        //世界高度测量值
        int lineHeight = 0;

        //当前width值
        int currentWidth = 0;
        //当前height值
        int currentHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == GONE) continue;

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (currentWidth + childWidth > widthSize - getPaddingLeft() - getPaddingRight()) { //需要换行
                lineWidth = Math.max(currentWidth, lineWidth);
                lineHeight += currentHeight;

                //到达下一行时 此时当前view的宽度和高度需要赋值
                currentHeight = childHeight;
                currentWidth = getPaddingLeft() + childWidth;
            } else {
                currentWidth += childWidth;
                currentHeight = Math.max(currentHeight, childHeight);

                if (i == childCount - 1) {
                    lineWidth = Math.max(lineWidth, childWidth);
                    lineHeight += currentHeight;
                }
            }

        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : lineWidth + getPaddingLeft() + getPaddingRight(),
                heightMode == MeasureSpec.EXACTLY ? heightSize : lineHeight + getPaddingTop() + getPaddingBottom());

        Log.d("checkLineHeight ", "the lineHeight is " + lineHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mViewContainer.clear();
        mEachLineContainer.clear();
        mViewContainer.clear();

        int childCount = getChildCount();
        int currentWidth = 0;
        int currentHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == GONE) continue;

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = lp.leftMargin + lp.rightMargin + childView.getMeasuredWidth();
            int childHeight = lp.topMargin + lp.bottomMargin + childView.getMeasuredHeight();

            if (currentWidth + childWidth > getWidth() - getPaddingLeft() - getPaddingRight()) {
                mViewContainer.add(mEachLineContainer);
                mLineHeightList.add(currentHeight);

                //can not clear , the pointer will make the previous container wrong..
                mEachLineContainer = new ArrayList<>();
                mEachLineContainer.add(childView);
                currentHeight = childHeight;
                currentWidth = childWidth;
            } else {
                currentHeight = Math.max(currentHeight, childHeight);
                currentWidth += childWidth;
                mEachLineContainer.add(childView);
            }

            if (i == childCount - 1) {
                mViewContainer.add(mEachLineContainer);
                currentHeight = Math.max(currentHeight, childHeight);
                mLineHeightList.add(currentHeight);
            }
        }

        int left = getPaddingLeft();
        int top = getPaddingTop();
        //依次画子类View
        for (int x = 0; x < mViewContainer.size(); x++) {
            List<View> echViewList = mViewContainer.get(x);
            int tempHeight = mLineHeightList.get(x);

            for (int y = 0; y < echViewList.size(); y++) {
                View ChildView = echViewList.get(y);
                MarginLayoutParams lp = (MarginLayoutParams) ChildView.getLayoutParams();

                ChildView.layout(left, top, left + ChildView.getMeasuredWidth(), top + ChildView.getMeasuredHeight());
                left += lp.leftMargin + lp.rightMargin + ChildView.getMeasuredWidth();

                Log.d("tag", "(x,y)" + x + "," + y + ", left : " + left + " , top : " + top);
            }

            left = getPaddingLeft();
            top += tempHeight;
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }


    public void setUserPagerData(List<ActiveTag> tags) {
        if(CommonUtils.collectionIsNull(tags)) return;

        TagView tagView ;
        for(ActiveTag tag : tags) {
            tagView = new TagView(getContext());
            tagView.setData(tag.name , tag.incr);
            addView(tagView,getDefaultParams());
        }
    }

    public void setUserBestTags(List<BestTag> tags) {
        if(CommonUtils.collectionIsNull(tags)) return;

        removeAllViews();
        TextView tv ;
        for(BestTag tag : tags) {
            tv = new TextView(getContext());
            tv.setBackgroundResource(R.color.app_shallow_theme_color);
            tv.setTextColor(getResources().getColor(R.color.app_theme_color));
            tv.setText(tag.name);
            tv.setPadding(10,5,10,5);
            addView(tv,getDefaultParams());
        }
    }

    private ViewGroup.MarginLayoutParams mParams ;
    private ViewGroup.MarginLayoutParams getDefaultParams() {
        if(null == mParams) {
            mParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mParams.setMargins(10,5,10,5);
        }
        return mParams;
    }

}
