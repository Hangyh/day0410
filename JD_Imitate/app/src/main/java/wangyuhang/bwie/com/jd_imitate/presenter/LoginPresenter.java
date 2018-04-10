package wangyuhang.bwie.com.jd_imitate.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.bean.LoginBean;
import wangyuhang.bwie.com.jd_imitate.modle.LoginRegisModle;
import wangyuhang.bwie.com.jd_imitate.view.LoginView;

/**
 * Created by dell on 2018/3/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView iveiw) {
        super(iveiw);
    }


    public void loginP(String mobiel,String password){

        new LoginRegisModle().loginModle(mobiel, password, new LoginRegisModle.LoginCallBack() {
            @Override
            public void LoginFai() {

            }

            @Override
            public void LoginSucess(LoginBean loginBean) {
                if (Iview!=null){
                    Iview.loginSuccess2(loginBean);
                }
            }
        });
    }
}
