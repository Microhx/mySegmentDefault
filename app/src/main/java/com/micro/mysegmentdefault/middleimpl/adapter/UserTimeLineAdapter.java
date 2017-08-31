package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserTimeLineDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewSupport;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewTypeAdapter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/28 - 14:41 <p>
 * interface :
 */

public class UserTimeLineAdapter extends MultiViewTypeAdapter<UserTimeLineDataEntity.DataItem> {

    public UserTimeLineAdapter(Context ctx) {
        super(ctx, new MultiViewSupport<UserTimeLineDataEntity.DataItem>() {
            @Override
            public int getLayoutId(int itemType) {
                switch (itemType) {
                    case 1:
                        return R.layout.user_time_line_item_layout;
                    case 2:
                        return R.layout.user_time_line_share_item_layout;
                    default:
                        return R.layout.user_time_line_tag_item_layout;
                }
            }

            @Override
            public int getItemViewType(int position, UserTimeLineDataEntity.DataItem dataItem) {
                switch (dataItem.rootObjectType) {
                    case "article":  //文章
                    case "blog":     //专栏
                    case "question": //问题
                        return  1 ;

                    case "share" : //头条
                        return 2 ;

                    default:  //用户&用户
                        return  0;
                }

            }
        });
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final UserTimeLineDataEntity.DataItem item, int position) {
        //onclick the TextView to StyleClickSpan
        ((TextView)holder.getView(R.id.id_tv_title)).setMovementMethod(LinkMovementMethod.getInstance());

        if(holder.getItemViewType() == 1) {  //文章 专栏 问题
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title, CommonUtils.replaceTargetWordWithAppThemeColor(item.sentence,UserLogic.getUserName(),item.user.url)).
                    setTextView(R.id.id_tv_time,item.date).
                    setTextView(R.id.id_tv_question_title,item.title).
                    setTextView(R.id.id_tv_question_content,item.excerpt);

            if(item.object == null || TextUtils.isEmpty(item.object.thumbnailUrl)) {
                holder.setViewGone(R.id.id_iv_image,true);

            }else {
                holder.setViewVisiable(R.id.id_iv_image,true);
                holder.setImageView(R.id.id_iv_image, item.object.thumbnailUrl);
            }

            holder.setViewVisiable(R.id.id_tv_other,"article".equals(item.rootObjectType));

        } else if(holder.getItemViewType() == 2) { //头条
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title,CommonUtils.replaceTargetWordWithAppThemeColor(item.sentence,UserLogic.getUserName(),item.user.url)).
                    setTextView(R.id.id_tv_time,item.date).
                    setTextView(R.id.id_tv_head,item.excerpt).
                    setTextView(R.id.id_tv_question_title,item.title);

            if(null == item.object ||TextUtils.isEmpty(item.object.readFirstImg)){
                holder.setImageViewDefault(R.id.id_iv_image,R.drawable.a_link);
            }else {
                holder.setImageView(R.id.id_iv_image,item.object.readFirstImg);
            }

        }else {   //TODO 用户图像为圆形
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title,CommonUtils.replaceTargetWordWithAppThemeColor(item.sentence,UserLogic.getUserName(),item.user.url)).
                    setTextView(R.id.id_tv_time,item.date).
                    setTextView(R.id.id_tv_question_title,item.title).
                    setTextView(R.id.id_tv_question_content,item.excerpt);

            if("user".equals(item.rootObjectType)) {
                holder.setImageView(R.id.id_iv_image,item.object.avatarUrl);
            }else{
                holder.setImageView(R.id.id_iv_image,item.object.thumbnailUrl);
            }
        }

        if("article".equals(item.rootObjectType) || "share".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.votes + " 赞同 · " + item.meta.comments +" 评论 · " + item.meta.bookmarks +" 收藏");

        }else if("question".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.followers + " 关注 · " + item.meta.answers +" 回答 · " + (item.meta.isAccepted ? "已解决" : "未解决"));

        }else if("tag".equals(item.rootObjectType) || "user".equals(item.rootObjectType) || "blog".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.followers + " 关注 " );
        }


        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(item.url)) {
                    Intent _intent = new Intent(mContext, SchemeActivity.class);
                    _intent.setData(Uri.parse(Api.WEB_URL + item.url));
                    _intent.putExtra("tagId", item.object.id);
                    mContext.startActivity(_intent);
                }else{
                    ToastUtils.showMessage(mContext,"url无效");
                }
            }
        });





    }


    /**
     * 1.头条
     * 2.问题
     * 3.文章
     * 6.专栏
     *
     * 4.用户
     * 5.标签
     *
     * */

}
