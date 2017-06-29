package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BaseStatePageAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.TagDetailArticleFragment;
import com.micro.mysegmentdefault.middleimpl.subfragment.TagDetailQuestionFragment;
import com.micro.mysegmentdefault.middleimpl.subfragment.TagDetailTopUserFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:42 <p>
 * interface :
 */

public class TagDetailPageAdapter extends BaseStatePageAdapter<String> {

    private String mTagId;

    public TagDetailPageAdapter(List<String> title, String mTagId, FragmentManager fm) {
        super(title, fm);
        this.mTagId = mTagId;
    }

    @Override
    public CharSequence getPageTitle(int position, String s) {
        return s;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment subFragment = new Fragment();
        if(position == 0) {
            subFragment = new TagDetailQuestionFragment();
        }else if(position == 1) {
            subFragment = new TagDetailArticleFragment() ;
        }else if(position == 2) {
            subFragment = new TagDetailTopUserFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putString("tagId", mTagId);
        subFragment.setArguments(bundle);

        return subFragment;
    }
}
