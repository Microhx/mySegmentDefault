package com.micro.mysegmentdefault.middle;

import android.widget.AbsSeekBar;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.view.AbsAksTagView;

import java.util.Map;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/1 - 16:53 <p>
 * interface :
 */

public interface AskQuestionContract {

    interface AbsAskQuestionModel extends BaseModel {
        Observable<OnlyData> addUserQuestion(Map<String,String> map);
    }

    interface AbsAskQuestionView extends BaseView {
        void showResult(OnlyData data);
    }

    abstract class AbsAskQuestionPresenter extends BasePresenter<AbsAskQuestionView,AbsAskQuestionModel> {
        public abstract void addUserQuestion(Map<String,String> map);

    }

}
