package com.micro.mysegmentdefault.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.TagDynamicDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
import static android.widget.ListPopupWindow.INPUT_METHOD_NEEDED;

/**
 * author : micro_hx <p>
 * desc : 用户选择标签页面 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 23:42 <p>
 * interface :
 */

public class UserChooseTagActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    /**
     * 服务器回传的tag ids
     */
    private final long[] TAG_IDS = { 1040000000089436L, 1040000000089387L, 1040000000089434L, 1040000000089534L,
                                     1040000000089571L, 1040000000089449L, 1040000000089918L, 1040000000089409L,
                                     1040000000089741L, 1040000000089457L, 1040000000090209L, 1040000000089669L,
                                      1040000000531223L, 1040000000090203L, 1040000000089699L, 1040000000089686L,
                                    1040000000089581L,
                                     1040000000090433L, 1040000000091262L, 1040000000161068L, 1040000000090208L,
                                    1040000000090228L, 1040000000090600L, 1040000000090194L, 1040000000089698L, 1040000000090201L,
                                    1040000000311191L, 1040000000365394L, 1040000000196640L, 1040000000125263L, 1040000000089564L,
                                    1040000000089646L, 1040000000166256L, 1040000000090103L, 1040000000116331L, 1040000000090398L,
                                    1040000000622042L, 1040000000089638L, 1040000000089392L, 1040000000090145L, 1040000000089761L,
                                    1040000000090245L, 1040000000366352L, 1040000000090249L, 1040000000090401L, 1040000000090117L,
                                    1040000000090646L, 1040000000089648L, 1040000000090107L, 1040000000090609L, 1040000000089439L,
                                    1040000000089431L, 1040000000090049L, 1040000000089488L, 1040000000089432L, 1040000000089873L,
                                    1040000000090354L, 1040000000090701L, 1040000000090424L, 1040000000090861L, 1040000000089459L,
                                    1040000000091226L, 1040000000089467L, 1040000000089579L, 1040000000126097L, 1040000000089509L,
                                    1040000000089447L, 1040000000090560L, 1040000000090473L, 1040000000090216L, 1040000000090524L,
                                    1040000000090186L, 1040000000090231L, 1040000002720957L, 1040000000089663L, 1040000000183694L,
                                    1040000000089442L, 1040000000089658L, 1040000000089407L, 1040000000089709L, 1040000000089498L,
                                    1040000000125504L, 1040000000260425L, 1040000000090217L, 1040000000090638L, 1040000000089499L,
                                    1040000000090218L, 1040000002871462L, 1040000000089409L, 1040000002893277L, 1040000000090137L,
                                    1040000000123810L, 1040000000090316L };

    /**
     * 所有的tag需要从这里选
     */
    private final String[] TAG_NAMES = { "javascript", "php", "css", "python", "html", "java",
                                        "node.js", "html5", "c++", "c", "objective-c", "shell", "swift", "golang", "ruby",
                                        "bash", "c#", "asp.net", "sass", "coffeescript", "lua", "scala", "less", "actionscript",
                                        "perl", "erlang", "rust", "typescript", "laravel", "flask", "django", "ruby-on-rails",
                                        "express", "spring", "tornado", "yii", "koa", "struts", "linux", "nginx", "apache",
                                        "ubuntu", "docker", "centos", "缓存", "tomcat", "unix", "hadoop", "负载均衡",
                                        "windows-server", "mysql", "redis", "sql", "mongodb", "nosql", "memcached",
                                        "sqlite", "oracle", "sqlserver", "postgresql", "git", "github", "vim", "xcode",
                                        "sublime-text", "eclipse", "svn", "intellij-idea", "ide", "emacs", "atom", "maven",
                                        "visual-studio", "visual-studio-code", "textmate", "hg", "ios", "android", "chrome",
                                        "windows", "iphone", "internet-explorer", "osx", "firefox", "ipad", "safari", "opera",
                                        "apple-watch", "html5", "react.js", "搜索引擎", "virtualenv", "lucene" };

    private ListPopupWindow mListPopupWindow;
    private ArrayAdapter<String> mArrayAdapter;

    private List<BestTag> mBestTagList;

    @Bind(R.id.id_et_content)
    EditText mEtContent;


    public static void start(Activity ctx, ArrayList<BestTag> tags,int requestCode){
        Intent _intent = new Intent(ctx,UserChooseTagActivity.class);
        _intent.putParcelableArrayListExtra("tags" , tags);
        ctx.startActivityForResult(_intent,requestCode);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mBestTagList = getIntent().getParcelableArrayListExtra("tags");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_choose_tag;
    }

    @Override
    protected void initViews() {
        mArrayAdapter = new ArrayAdapter(this,R.layout.item_choose_new_tag,R.id.id_tv_tag,TAG_NAMES);
        mListPopupWindow = new ListPopupWindow(this);
        mListPopupWindow.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE);
        mListPopupWindow.setInputMethodMode(INPUT_METHOD_NEEDED);
        mListPopupWindow.setAnchorView(mEtContent);
        mListPopupWindow.setAdapter(mArrayAdapter);
        mListPopupWindow.setBackgroundDrawable(new ColorDrawable(-1));
        mListPopupWindow.setOnItemClickListener(this);

        mEtContent.addTextChangedListener(new CustomTextWatcher());

        initAddingTags();
    }

    private void initAddingTags() {
        if(CommonUtils.collectionIsNull(mBestTagList)) return;

        SpannableStringBuilder localBuilder = new SpannableStringBuilder();
        int index = 0 ;
        for(BestTag bestTag : mBestTagList) {
            String name = bestTag.name;
            int length = name.length();
            int last = index + length;

            localBuilder.append(name);
            localBuilder.setSpan(new TagDynamicDrawable(bestTag),index,last,33);

            index += length;
        }

        mEtContent.getText().replace(0,0,localBuilder,0,localBuilder.length());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String str = mArrayAdapter.getItem(position);
        int index = getPositionIndex(str);

        BestTag tag = new BestTag();
        tag.name = str;
        tag.id = String.valueOf(TAG_IDS[index]);

        Editable localEditable = mEtContent.getText();
        int length = localEditable.length();

        TagDynamicDrawable[] arrays = localEditable.getSpans(0,length,TagDynamicDrawable.class);
        SpannableStringBuilder sb = new SpannableStringBuilder();

        for(int i = 0 ; i < arrays.length; i++){
            if(str.equals(arrays[i].getBestTag().name)) {
                return;
            }
        }

        int index2 = 0 ;
        for(TagDynamicDrawable drawable : arrays){
            String tagName = drawable.getBestTag().name;
            int length2 = tagName.length();

            sb.append(tagName);
            sb.setSpan(drawable,index2,index2+length2,33);
            index2 += length2;
        }

        sb.append(str);
        sb.setSpan(new TagDynamicDrawable(tag),index2,index2+str.length(),33);
        localEditable.replace(0,length,sb,0,sb.length());
    }


    private int getPositionIndex(String str) {
        int index = 0;
        for(int i = 0 ; i < TAG_NAMES.length ; i++) {
            if(str.equals(TAG_NAMES[i])){
                return i;
            }
        }
        return index;
    }

    @OnClick(R.id.id_iv_right)
    public void onCommit(View v) {
        ArrayList<BestTag> _list = new ArrayList<>();

        Editable editable = mEtContent.getText();
        TagDynamicDrawable[] arrays = editable.getSpans(0,editable.length() , TagDynamicDrawable.class);
        for(TagDynamicDrawable drawable : arrays){
            _list.add(drawable.getBestTag());
        }

        setResult(RESULT_OK,new Intent().putParcelableArrayListExtra("tags",_list));
        finish();
    }

    private class CustomTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(TextUtils.isEmpty(s)) return;

            Editable localEditable = mEtContent.getText();
            TagDynamicDrawable[] arrays = localEditable.getSpans(0,localEditable.length(),TagDynamicDrawable.class);
            if(null == arrays || arrays.length == 0) return;

            int arrayLength = arrays.length;
            int edLength = mEtContent.getSelectionEnd();
            int lastOne = localEditable.getSpanEnd(arrays[arrayLength-1]);

            if(lastOne > 0) {
                String str = s.subSequence(lastOne,edLength).toString();
                if(TextUtils.isEmpty(str)){
                    mListPopupWindow.dismiss();
                    return;
                }

                mListPopupWindow.setInputMethodMode(SOFT_INPUT_STATE_UNCHANGED);
                mListPopupWindow.show();
                mListPopupWindow.getListView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
                mArrayAdapter.getFilter().filter(str);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }

}
