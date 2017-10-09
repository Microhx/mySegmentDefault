package com.micro.mysegmentdefault.middleimpl.fragment;

import com.micro.mysegmentdefault.base.module.BaseFragment;

/**
 * author : micro_hx <p>
 * desc : 获取fragment集合<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 15:55 <p>
 * interface :
 */

public class FragmentUtils {

    public static BaseFragment getBaseFragmentByIndex(int index) {
        switch (index) {
            default:
            case 0:
                return new ToutiaoFragment();
            case 1:
                return new ArticleFragment();
            case 2:
                return new QuestionsFragment() ;
            case 3 :
                return new DiscoveryFragment() ;
            case 4:
                return new MineFragment() ;
        }

    }



}
