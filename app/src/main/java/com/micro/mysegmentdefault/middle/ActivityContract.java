package com.micro.mysegmentdefault.middle;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:43 <p>
 * interface :
 */

public interface ActivityContract {

    interface ActivityModel extends BaseModel {

    }

    interface ActivityView extends BaseView {

    }

    abstract class Presenter extends BasePresenter<ActivityView,ActivityModel> {

    }

}
