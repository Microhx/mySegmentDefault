package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;

import java.util.List;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 11:20 <p>
 * interface :
 */

public interface MultipleSearchContract {

    interface AbsMultipleSearchView extends BaseView {
        void showHistorySearchResult(List<String> result);

        Context getContext();

    }

    interface AbsMultipleSearchModel extends BaseModel {
        Observable<List<String>> loadUserHistorySearch(Context ctx);
    }

    abstract class AbsMutipleSearchPresenter extends BasePresenter<AbsMultipleSearchView,AbsMultipleSearchModel> {
        public abstract void loadUserHistorySearch();
    }


}
