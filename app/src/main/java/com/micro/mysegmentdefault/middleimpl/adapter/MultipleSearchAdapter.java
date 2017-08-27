package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewSupport;
import com.micro.mysegmentdefault.middleimpl.adapter.multiple.MultiViewTypeAdapter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 16:11 <p>
 * interface :
 */

public class MultipleSearchAdapter extends MultiViewTypeAdapter<SearchDataEntity.SearchItem> {

    private String mKeyWords;

    public MultipleSearchAdapter(Context ctx,String keyWords) {
        super(ctx, new MultiViewSupport<SearchDataEntity.SearchItem>() {
            @Override
            public int getLayoutId(int itemType) {
                switch (itemType) {
                    default:
                    case 0:  //tag
                        return R.layout.user_search_tag_item_layout;

                    case 1: //question
                        return R.layout.search_question_item_layout ;

                    case 2:  //article
                        return R.layout.note_item_layout;

                    case 3: //user
                        return R.layout.search_user_item_layout;
                }
            }

            @Override
            public int getItemViewType(int position, SearchDataEntity.SearchItem searchItem) {
                    switch (searchItem.type) {
                        default:case "tag" : return 0 ;
                        case "question" : return 1;
                        case "article" : return 2;
                        case "user" : return 3;
                    }
            }
        });

        this.mKeyWords = keyWords;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final SearchDataEntity.SearchItem item, int position) {
        switch (item.type) {
            default:
            case "tag":
                holder.setTextView(R.id.id_tv_result,item.name);
                break;

            case "question":
                holder.setTextView(R.id.id_tv_answer_content,CommonUtils.replaceTargetWordWithRed(item.title,mKeyWords));
                holder.setTextView(R.id.id_tv_answer_count,item.answers);
                holder.setAcceptText(R.id.id_tv_answer_count,item.answers,CommonUtils.safeParseBoolean(item.isAccepted));
                holder.setTextView(R.id.id_tag_layout , CommonUtils.convertTag2String(item.tags));
                break;

            case "article":
                holder.setTextView(R.id.id_tv_username,item.user.name);
                holder.setImageView(R.id.id_iv_user_icon,item.user.avatarUrl);
                holder.setTextView(R.id.id_tv_qus_title,item.title);
                holder.setTextView(R.id.id_tv_qus_content,item.excerpt);
                holder.setTextView(R.id.id_tv_qus_comment, item.votes+"人点赞 " + item.bookmarks+"人收藏");
                break;

            case "user":
                holder.setImageView(R.id.id_iv_user_icon,item.avatarUrl);
                holder.setTextView(R.id.id_iv_user_name,item.name);
                holder.setTextView(R.id.id_tv_user_id," @"+item.slug);
                holder.setTextView(R.id.id_tv_rank,item.rank);
                break;
        }

        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(item.url)) {
                    Intent _intent = new Intent(mContext, SchemeActivity.class);
                    _intent.setData(Uri.parse(Api.WEB_URL + item.url));
                    _intent.putExtra("tagId", item.id);
                    mContext.startActivity(_intent);
                }else{
                    ToastUtils.showMessage(mContext,"url无效");
                }
            }
        }) ;
    }

    public void setKeyWords(String mKeyWords) {
        this.mKeyWords = mKeyWords;
    }
}
