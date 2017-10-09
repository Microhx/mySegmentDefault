package com.micro.mysegmentdefault.ui.user.attention;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserAttentionPersonDataEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserAttentionPersonModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserAttentionPersonPresenter;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 22:07 <p>
 * interface :
 */

public class UserAttentionPersonActivity extends
        AbBaseAttentionActivity<UserAttentionPersonPresenter, UserAttentionPersonModel, UserAttentionPersonDataEntity.AttentionPersonItem> {

    @Override
    protected void initViews() {
        super.initViews();
        mTvTitle.setText(R.string.str_attention_person);
    }

    //TODO what's this number means...
    @Override
    protected String getDefaultChannel() {
        return "1030000007112343" ;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.user_attention_person_item_layout;
    }

    @Override
    protected void convertData(ViewHolderHelper holder,final UserAttentionPersonDataEntity.AttentionPersonItem item , int position) {
        holder.setUserCicrleImageView(R.id.id_iv_icon, item.avatarUrl).
                setTextView(R.id.id_tv_title, item.name).
                setTextView(R.id.id_tv_slug, "@" + item.slug).
                setTextView(R.id.id_tv_reputation, "声望 " + item.likedVotes).
                setTextView(R.id.id_tv_fans, "粉丝 " + item.followedUsers);

        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserZoneActivity.start(UserAttentionPersonActivity.this,item.slug);
            }
        }) ;

    }
}
