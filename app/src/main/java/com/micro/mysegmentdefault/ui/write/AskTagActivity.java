package com.micro.mysegmentdefault.ui.write;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.base.mvp.view.BaseRefreshView;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsAksTagView;
import com.micro.mysegmentdefault.middleimpl.adapter.listbase.AskTagAdapter;
import com.micro.mysegmentdefault.middleimpl.fragment.CreateTagFragment;
import com.micro.mysegmentdefault.middleimpl.mvp.model.SearchSubModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.SearchSubPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.TagDynamicDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
import static android.widget.ListPopupWindow.INPUT_METHOD_NEEDED;

/**
 * 选择标签
 */
public class AskTagActivity extends BaseActivity<SearchSubPresenter, SearchSubModel>  implements AdapterView.OnItemClickListener, BaseRefreshView<SearchDataEntity.SearchItem>,CreateTagFragment.OnEmailFinishListener, AbsAksTagView<SearchDataEntity.SearchItem> {

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

        initAddingTags();
    }

    private void initAddingTags() {
     ArrayList<BestTag> bestTags =  getIntent().getParcelableArrayListExtra("bestTags");
     if(CommonUtils.collectionIsNull(bestTags)) return;

     SpannableStringBuilder sb = new SpannableStringBuilder();
        int index = 0 ;
        for(BestTag tag : bestTags) {
            String name = tag.name;
            int length = name.length();

            sb.append(name);
            sb.setSpan(new TagDynamicDrawable(tag),index,index+length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            index += length;
        }
        mEtContent.getText().append(sb);
    }


    @OnTextChanged(R.id.id_et_content)
    public void onTextChange(CharSequence s , int start, int before,int count) {

        if(TextUtils.isEmpty(s)) return;
        Editable localEditable = mEtContent.getText();
        int length = localEditable.length();

        TagDynamicDrawable[] tagArray = localEditable.getSpans(0,length,TagDynamicDrawable.class);

        if(null == tagArray || tagArray.length == 0) {
            sendSearchOperation(s.toString());
        }else {
            int endSpanIndex = localEditable.getSpanEnd(tagArray[tagArray.length-1]);
            String lastStr = s.subSequence(endSpanIndex,length).toString();
            if(!TextUtils.isEmpty(lastStr)) {
                sendSearchOperation(lastStr);
            }
        }
    }

    @OnClick(R.id.id_iv_right)
    public void onCommit(View v) {
        Editable localEditable = mEtContent.getText();
        TagDynamicDrawable[] tagArray = localEditable.getSpans(0,localEditable.length(),TagDynamicDrawable.class);
        ArrayList<BestTag> bestTags = new ArrayList<>();
        for(TagDynamicDrawable ty : tagArray) {
            bestTags.add(ty.getBestTag());
        }

        setResult(RESULT_OK,new Intent().putParcelableArrayListExtra("bestTags",bestTags));
        finish();

    }

    /**
     * 发送
     * @param str
     */
    private void sendSearchOperation(String str) {
        Message msg = mSearchHandler.obtainMessage();
        msg.obj = str ;

        mSearchHandler.removeCallbacksAndMessages(null);
        mSearchHandler.sendMessageDelayed(msg,700);
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
        if(position == 0 && TextUtils.isEmpty(mAskTagAdapter.getItem(0).name)) {
            showCreateNewTagDialog(mAskTagAdapter.getTagName());
        }else {
            String tagName = mAskTagAdapter.getItem(position).name;
            String tagId = mAskTagAdapter.getItem(position).id ;

            BestTag bestTag = new BestTag();
            bestTag.id = tagId;
            bestTag.name = tagName;

            Editable localEditable = mEtContent.getText();
            int length = localEditable.length();

            TagDynamicDrawable[] arrays = localEditable.getSpans(0,length,TagDynamicDrawable.class);
            SpannableStringBuilder sb = new SpannableStringBuilder();

            for(int i = 0 ; i < arrays.length ; i++) {
                if(tagName.equals(arrays[i].getBestTag().name)) {
                    return ;
                }
            }

            int index = 0;
            for(TagDynamicDrawable drawable : arrays) {
                String thatName = drawable.getBestTag().name;
                int thatLength = thatName.length();

                sb.append(thatName);
                sb.setSpan(drawable,index,index+thatLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                index += thatLength;
            }

            sb.append(tagName);
            sb.setSpan(new TagDynamicDrawable(bestTag),index,index + tagName.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            localEditable.replace(0,length,sb,0,sb.length());
        }

        mListPopupWindow.dismiss();
    }

    private void showCreateNewTagDialog(String tagName) {
        CreateTagFragment tagFragment = new CreateTagFragment();
        tagFragment.setTagName(tagName);
        tagFragment.setListener(this);
        tagFragment.show(getFragmentManager(),"tag");
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
            }
        }
    };

    @Override
    public void onFinish(String tagName ,String tagDesc) {
        if(!TextUtils.isEmpty(tagName) && !TextUtils.isEmpty(tagDesc) ) {
            mPresenter.addUserNewTag(tagName,tagDesc);
        }
    }

    @Override
    public void showUserNewTag(boolean result) {
        //TODO should we get the id ,and add this tag into the editText
        if(result) {
            showToast(R.string.str_create_tag_success);
        }else {
            showToast(R.string.str_operation_error);
        }
    }
}
