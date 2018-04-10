package wangyuhang.bwie.com.jd_imitate.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;
import wangyuhang.bwie.com.jd_imitate.modle.ShowModle;
import wangyuhang.bwie.com.jd_imitate.modle.ShowModleSou;
import wangyuhang.bwie.com.jd_imitate.view.ShowView;
import wangyuhang.bwie.com.jd_imitate.view.ShowViewSou;

/**
 * Created by dell on 2018/3/17.
 */

public class ShowPrensenterSou extends BasePresenter<ShowViewSou> {


    public ShowPrensenterSou(ShowViewSou iview) {
        super(iview);
    }

    public void showP(String token, String et, int page){


        new ShowModleSou().showM(token,et, page, new ShowModleSou.showListenner() {
            @Override
            public void showFail() {

            }

            @Override
            public void showSuccess(ShowBean showBean) {

                if (Iview!=null){
                    Iview.showDataSuccess(showBean);
                }

            }
        });
    }
}
