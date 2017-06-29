package com.micro.mysegmentdefault.middleimpl.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.ShareItemEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.BottomShareRecyclerAdapter;
import com.micro.mysegmentdefault.network.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : 从底部而来的分享Fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/22 - 22:47 <p>
 * interface :
 */

public class BottomShareFragment extends BottomSheetDialogFragment {

    //文章head
    private String mTitle ;

    //文章描述
    private String mDesc ;

    //文章目标URL
    private String mUrl ;

    @Bind(R.id.id_recy_view)
    RecyclerView mRecyclerView ;

    private BottomShareRecyclerAdapter mBottomShareAdapter;

    private List<ShareItemEntity> mShareItemList ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mTitle = bundle.getString("title");
        mDesc = bundle.getString("desc");
        mUrl = bundle.getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_share_layout,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        mBottomShareAdapter = new BottomShareRecyclerAdapter(getContext());
        mRecyclerView.setAdapter(mBottomShareAdapter);

        loadSystemShareItems();
        mBottomShareAdapter.addAll(mShareItemList);
    }

    private void loadSystemShareItems() {
        mShareItemList = new ArrayList<>();

        Intent _intent = new Intent();
        _intent.setAction("android.intent.action.SEND");
        _intent.setType("text/plain");
        PackageManager localPackManager = getContext().getPackageManager();

        List<ResolveInfo> infos = getContext().getPackageManager().queryIntentActivities(_intent, 0);
        for(ResolveInfo resolveInfo : infos) {
            CharSequence title = resolveInfo.loadLabel(localPackManager) ;
            Drawable icon = resolveInfo.loadIcon(localPackManager);

            String packName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent innerIntent = new Intent();
            innerIntent.setAction("android.intent.action.SEND");
            innerIntent.setType("text/paint");
            innerIntent.putExtra("android.intent.extra.TEXT",getExtraInfo());

            innerIntent.setClassName(packName,className);

            this.mShareItemList.add(new ShareItemEntity(icon,innerIntent,title.toString()));
        }
    }

    private String getExtraInfo() {
        return "【"+mTitle+"】" + "分享来自@mySegmentFault,传送门:" + Api.WEB_URL + mUrl;
    }


}
