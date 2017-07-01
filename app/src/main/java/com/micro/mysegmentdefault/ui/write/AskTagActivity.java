package com.micro.mysegmentdefault.ui.write;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.listbase.AskTagAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.SearchSubModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.SearchSubPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnTextChanged;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
import static android.widget.ListPopupWindow.INPUT_METHOD_NEEDED;

/**
 * 选择标签
 */
public class AskTagActivity extends BaseActivity<SearchSubPresenter, SearchSubModel>  implements AdapterView.OnItemClickListener, BaseRefreshView<SearchDataEntity.SearchItem> {

    @Bind(R.id.id_et_content)
    EditText mEtContent;

    private ListPopupWindow mListPopupWindow;

    private AskTagAdapter mAskTagAdapter;

    private String mSearchKeyWords;


    @Override
    protected void initViews() {
        mAskTagAdapter = new AskTagAdapter();
        mListPopupWindow = new ListPopupWindow(this);
        mListPopupWindow.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE);
        mListPopupWindow.setInputMethodMode(INPUT_METHOD_NEEDED);
        mListPopupWindow.setAnchorView(mEtContent);
        mListPopupWindow.setAdapter(mAskTagAdapter);
        mListPopupWindow.setBackgroundDrawable(new ColorDrawable(-1));
        mListPopupWindow.setOnItemClickListener(this);
    }


    @OnTextChanged(R.id.id_et_content)
    public void onTextChange(CharSequence s , int start, int before,int count) {
        String str = String.valueOf(s);
        if(!TextUtils.isEmpty(str)){
            Message msg = mSearchHandler.obtainMessage();
            msg.obj = str ;

            mSearchHandler.removeCallbacksAndMessages(null);
            mSearchHandler.sendMessageDelayed(msg,800);
        }
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_choose_tag ;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    //ListPopupWindow
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //get
    @Override
    public void getCommonListDatas(int startPages, List<SearchDataEntity.SearchItem> mDataList, PageEntity pageEntity) {
        convertData(mDataList);
    }

    private void convertData(List<SearchDataEntity.SearchItem> mDataList) {
        //清除原有数据
        mAskTagAdapter.clear();
        //重新加载数据
        if(CommonUtils.collectionIsNull(mDataList)){
            mAskTagAdapter.setSingleData(mSearchKeyWords,true);
        }else {
            mAskTagAdapter.setDatas(mDataList,false);
        }

        mListPopupWindow.setInputMethodMode(SOFT_INPUT_STATE_UNCHANGED);
        mListPopupWindow.show();
        mListPopupWindow.getListView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);

    }

    @Override
    public void getRequestError(int startPage) {
        LogUtils.d("getRequestError:"+startPage);
    }

    private Handler mSearchHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(null != msg) {
                String key = msg.obj.toString();
                mSearchKeyWords = key;

                mPresenter.getCommonListDatas(3,key,1);

                LogUtils.d("-------------------->>>get data------------>>" + key);

            }
        }
    };



}
