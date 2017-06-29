package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.MessageDataEntity;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.ui.comment.WebBrowserActivity;
import com.micro.mysegmentdefault.utils.HTMLMedia;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.StyleClickSpan;

import java.util.Iterator;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/7 - 15:26 <p>
 * interface :
 */

public class MessageRecyclerAdapter extends BaseRecyclerAdapter<MessageDataEntity.Item> {


    public MessageRecyclerAdapter(Context ctx) {
        super(ctx,ONLY_FOOTER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_message_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final MessageDataEntity.Item item, int position) {
        MessageViewHolder messageHolder = (MessageViewHolder) holder;
        messageHolder.setTitle(item.detail);
        messageHolder.mTvContent.setText(item.excerpt);

        messageHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自来系统的消息  相关徽章的获得者
                if("37".equals(item.type)) {
                    String url = Api.WEB_URL + item.url;
                    LogUtils.d("----target url----->>" + url);

                    WebBrowserActivity.start(mContext,url);
                }else if("36".equals(item.type)) { //进入自己空间
                    //TODO
                    LogUtils.d("----type 36----------->>> bind...");

                }else { //其他默认进入SchemeActivity中
                    Uri localUri = Uri.parse(item.url);
                    Intent _intent = new Intent(mContext,SchemeActivity.class);
                    _intent.setData(localUri);
                    _intent.putExtra("inner",true);
                    mContext.startActivity(_intent);
                }
            }
        });
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvContent ;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.id_tv_title);
            mTvContent = (TextView) itemView.findViewById(R.id.id_tv_content);

            mTvTitle.setMovementMethod(LinkMovementMethod.getInstance());
            mTvTitle.setFocusable(false);
        }


        public void setTitle(String title) {
            HTMLMedia media = new HTMLMedia(title);
            SpannableStringBuilder localSb = new SpannableStringBuilder(media.getDisplayString());
            Iterator<HTMLMedia.HTMLMediaItem> iterator = media.getItems().iterator();
            while(iterator.hasNext()) {
                HTMLMedia.HTMLMediaItem item = iterator.next();
                String linkTitle = item.mLinkString;
                if(!linkTitle.startsWith("http") ) {
                    linkTitle = "sfclient:" + linkTitle;
                }
                localSb.setSpan(new StyleClickSpan(linkTitle),item.start,item.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mTvTitle.setText(localSb);
        }
    }

}
