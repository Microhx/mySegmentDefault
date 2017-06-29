package com.micro.mysegmentdefault.ui.setting;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户返回吐槽 界面
 *
 *   ------>>>>   http://mta.qq.com/mta/api/ctr_feedback/add_feedback
 *
 Content-Disposition: form-data; name="OSVersion"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	5.1.1

 Content-Disposition: form-data; name="AppKey"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	Aqc1104517053

 Content-Disposition: form-data; name="Model"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	Mi-4c

 Content-Disposition: form-data; name="Content"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	还好发自用户(email): null

 Content-Disposition: form-data; name="UserName"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	user

 Content-Disposition: form-data; name="AppVersion"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	4.3.4

 Content-Disposition: form-data; name="FileType"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	img

 Content-Disposition: form-data; name="TimeStamp"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	1498577429

 Content-Disposition: form-data; name="Sign"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	ae7be9ff9a8c8f25ec00dd3513010b26

 Content-Disposition: form-data; name="NetWork"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	WIFI

 Content-Disposition: form-data; name="Mid"
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit	77816c0a144ac1f548cd91d72f7034a3e3ed0ae0

 Content-Disposition: form-data; name="log"; filename="feedback.log"
 Content-Type: application/octet-stream; charset=UTF-8	<file>
 *
 *
 */
public class FeedBackActivity extends BaseActivity {

    @Bind(R.id.id_et_content)
    EditText mEtContent ;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_feed_back ;
    }

    @OnClick(R.id.id_tv_right)
    public void onCommit(View v) {
        String content = mEtContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            showToast(R.string.str_feed);
            return;
        }

        showToast(R.string.str_feed_success);
        finish();
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }
}
