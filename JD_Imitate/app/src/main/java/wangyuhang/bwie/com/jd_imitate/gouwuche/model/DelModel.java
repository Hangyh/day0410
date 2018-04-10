package wangyuhang.bwie.com.jd_imitate.gouwuche.model;

import io.reactivex.Flowable;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.MessageBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.presenter.DelPresenter;
import wangyuhang.bwie.com.jd_imitate.gouwuche.utils.RetrofitUtils;

/**
 * Created by dell on 2018/4/9.
 */

public class DelModel implements IModel {
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}