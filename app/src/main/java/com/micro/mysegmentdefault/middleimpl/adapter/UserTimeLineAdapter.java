package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserTimeLineDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewSupport;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewTypeAdapter;
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

                    default:
                        return  0;
                }

            }
        });
    }

    @Override
    protected void convertData(ViewHolderHelper holder, UserTimeLineDataEntity.DataItem item, int position) {
        if(holder.getItemViewType() == 1) {  //文章 专栏 问题
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title,item.sentence).
                    setTextView(R.id.id_tv_time,item.date).
                    setImageView(R.id.id_iv_image,item.object.thumbnailUrl).
                    setTextView(R.id.id_tv_question_title,item.title).
                    setTextView(R.id.id_tv_question_content,item.excerpt);

        } else if(holder.getItemViewType() == 2) { //头条
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title,item.sentence).
                    setTextView(R.id.id_tv_time,item.date).
                    setTextView(R.id.id_tv_head,item.object.description).
                    setImageView(R.id.id_iv_image,item.object.readFirstImg);
        }else {   //用户
            holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl).
                    setTextView(R.id.id_tv_title,item.sentence).
                    setTextView(R.id.id_tv_time,item.date).
                    setImageView(R.id.id_iv_image,item.object.thumbnailUrl).
                    setTextView(R.id.id_tv_question_title,item.title).
                    setTextView(R.id.id_tv_question_content,item.excerpt);
        }

        if("article".equals(item.rootObjectType) || "share".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.votes + " 赞同 · " + item.meta.comments +" 评论 · " + item.meta.bookmarks +" 收藏");

        }else if("question".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.followers + " 关注 · " + item.meta.answers +" 回答 · " + (item.meta.isAccepted ? "已解决" : "未解决"));

        }else if("tag".equals(item.rootObjectType) || "user".equals(item.rootObjectType) || "blog".equals(item.rootObjectType)) {
            holder.setTextView(R.id.id_tv_msg, item.meta.followers + " 关注 " );
        }

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
