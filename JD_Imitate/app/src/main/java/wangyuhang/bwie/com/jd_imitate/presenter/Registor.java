package wangyuhang.bwie.com.jd_imitate.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.bean.RegisBean;
import wangyuhang.bwie.com.jd_imitate.modle.LoginRegisModle;
import wangyuhang.bwie.com.jd_imitate.view.RegistorIvew;

/**
 * Created by dell on 2018/3/17.
 */

public class Registor extends BasePresenter<RegistorIvew> {


    public Registor(RegistorIvew iveiw) {
        super(iveiw);
    }

    public void regisP(String mobiel, String password){

        new LoginRegisModle().Registor(mobiel, password, new LoginRegisModle.ResoponsCallBack() {
            @Override
            public void ResgitFail() {
                if (Iview!=null){
                    Iview.regisFail2();
                }
            }

            @Override
            public void ResgitSuccess(RegisBean regisBean) {
                if (Iview!=null){
                    Iview.regisSuccess2(regisBean);
                }
            }
        });
    }
}
