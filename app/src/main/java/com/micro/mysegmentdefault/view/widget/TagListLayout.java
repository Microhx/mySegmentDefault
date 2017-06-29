package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 项目著作/学习经历<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/5 - 15:33 <p>
 * interface :
 */

public class TagListLayout extends LinearLayout {

    private LinearLayout.LayoutParams mParams ;

    private onItemClickListener mListener ;


    public TagListLayout(Context context) {
        this(context,null);
    }

    public TagListLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    /**
     *
     * @param list
     * @param type 0为project
     *             1为study
     *             2为work
     */
    public void addDataList(List<UserDetailDataEntity.BaseDataEntity> list, int type) {
        if(CommonUtils.collectionIsNull(list)) return;

        removeAllViews();
        for(UserDetailDataEntity.BaseDataEntity entity : list) {
            if(type == 0) {
                addData(entity.name,entity.description,type);
            }else if(type == 1) {
                addData(entity.name,entity.department,type);
            }else {
                addData(entity.name,entity.role,type);
            }

        }
    }

    //List<TagUploadOtherDataEntity.Item>
    public void addDataList2(List<TagUploadOtherDataEntity.Item> list, int type) {
        if(CommonUtils.collectionIsNull(list)) return;

        removeAllViews();
        for(TagUploadOtherDataEntity.Item entity : list) {
            if(type == 0) {
                addData(entity.name,entity.description,type);
            }else if(type == 1) {
                addData(entity.name,entity.department,type);
            }else {
                addData(entity.name,entity.role,type);
            }
        }
    }



    public void addData(final String title , final String content, int type) {
        final View rootView = LayoutInflater.from(getContext()).inflate(R.layout.tag_list_item_layout,this,false);
        rootView.setTag(type);
        TextView tvTitle = (TextView) rootView.findViewById(R.id.id_tv_title);
        TextView tvContent = (TextView) rootView.findViewById(R.id.id_tv_content);
        tvTitle.setText(title);
        tvContent.setText(content);

        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener) {
                    int sort = getViewInPosition(rootView);
                    int type = CommonUtils.safeParseInt(rootView.getTag()+"");
                    mListener.itemClick(type,sort,title,content);
                }
            }
        });

        addView(rootView,getDefaultParams());
    }

    private int getViewInPosition(View targetView) {
        int position =0 ;
        for(int i = 0 ; i < getChildCount() ; i++) {
            if(targetView == getChildAt(i)){
                position = i;
                break;
            }
        }

        return position;
    }



    private LinearLayout.LayoutParams getDefaultParams() {
        if(null == mParams) {
            mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) ;
        }
        return mParams;
    }

    public void setOnItemClickListener(onItemClickListener mListener) {
        this.mListener = mListener;
    }


    public interface onItemClickListener {

        /**
         * @param type 类型0为project 1为study 2为work
         * @param sort 当前view在LinearLayout中所排的位置
         * @param title 当前传入的title值
         * @param content 当前传入的content值
         */
        void itemClick(int type ,int sort  , String title , String content);
    }

}
