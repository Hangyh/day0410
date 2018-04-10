package wangyuhang.bwie.com.jd_imitate.gouwuche.model;

import java.util.List;

import io.reactivex.Flowable;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.DatasBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.MessageBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.presenter.NewsPresenter;
import wangyuhang.bwie.com.jd_imitate.gouwuche.utils.RetrofitUtils;

/**
 * Created by dell on 2018/4/9.
 */


public class NewsModel  implements IModel{
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter){
        this.presenter = (NewsPresenter) presenter;

    }
    @Override
    public void getData(String uid,String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}