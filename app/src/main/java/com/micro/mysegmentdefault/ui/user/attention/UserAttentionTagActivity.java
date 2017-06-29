package com.micro.mysegmentdefault.ui.user.attention;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserAttentionTagDataEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserAttentionTagModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAttentionTagPresenter;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 用户关注的标签 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/15 - 18:55 <p>
 * interface :
 */

public class UserAttentionTagActivity extends AbBaseAttentionActivity<UserAttentionTagPresenter,UserAttentionTagModel,UserAttentionTagDataEntity.AttentionTagItem> {

    @Override
    protected void initViews() {
        super.initViews();
        mTvTitle.setText(R.string.str_tags);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.user_attention_tag_item_layout;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, UserAttentionTagDataEntity.AttentionTagItem item, int position) {
        holder.setTextView(R.id.id_tv_tag_name,item.name)
              .setTextView(R.id.id_tv_attention_count,"关注"+item.followers)
              .setTextView(R.id.id_tv_content,item.excerpt);


    }
}
