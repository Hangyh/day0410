package wangyuhang.bwie.com.jd_imitate.view;


import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;

/**
 * Created by dell on 2018/3/17.
 */

public interface ShowViewSou extends BaseView{

    void showDataFail();

    void showDataSuccess(ShowBean showBean);

}
