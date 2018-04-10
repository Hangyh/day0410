package wangyuhang.bwie.com.jd_imitate.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.bean.LoginBean;

/**
 * Created by dell on 2018/3/17.
 */

public interface LoginView extends BaseView {

    void loginFail2();

    void loginSuccess2(LoginBean loginBean);



}
