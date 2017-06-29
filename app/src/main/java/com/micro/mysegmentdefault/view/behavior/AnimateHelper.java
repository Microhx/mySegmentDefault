package com.micro.mysegmentdefault.view.behavior;

/**
 * author : micro_hx <p>
 * desc : 动画展示/隐藏接口<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/21 - 23:07 <p>
 * interface :
 */

public interface AnimateHelper {


    int STATE_SHOW = 0x1 ;
    int STATE_HIDE = 0x2 ;

    void show() ;

    void hide() ;

}
