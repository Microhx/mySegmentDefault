package com.micro.mysegmentdefault.base.module;


import com.micro.mysegmentdefault.utils.ToastUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/22 - 13:41 <p>
 * interface :
 */

public abstract class BaseMainActivity extends BaseActivity{

    public static final int TIME_BETWEEN_EXIT = 1500 ;

    private long lastTimeCount ;

    @Override
    public void onBackPressed() {
        if(shouldControlBackPress()){
            long currentTime = System.currentTimeMillis() ;
            if(currentTime - lastTimeCount > TIME_BETWEEN_EXIT) {
                lastTimeCount = System.currentTimeMillis() ;
                ToastUtils.showMessage(this,"再按一次退出应用");
            }else {
                finish();
            }
        }else {
            super.onBackPressed();
        }
    }


    /**
     * 是否控制后退键
     * @return
     */
    protected boolean shouldControlBackPress() {
        return true ;
    }



}

