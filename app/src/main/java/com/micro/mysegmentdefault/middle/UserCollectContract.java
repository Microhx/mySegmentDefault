package com.micro.mysegmentdefault.middle;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 16:53 <p>
 * interface :
 */

public interface UserCollectContract {

    interface  AbsUserCollectModel<D> extends CommonContract.CommonModel<D> {

    }

    interface AbsUserCollectView<D> extends CommonContract.CommonView<D> {

    }

    abstract class AbsUserCollectPresenter extends CommonContract.Presenter {

    }



}
