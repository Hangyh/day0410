package wangyuhang.bwie.com.jd_imitate.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;
import wangyuhang.bwie.com.jd_imitate.modle.UserModle;
import wangyuhang.bwie.com.jd_imitate.view.UserView;

/**
 * Created by dell on 2018/3/17.
 */

public class UsetPrensenter extends BasePresenter<UserView> {


    public UsetPrensenter(UserView iveiw) {
        super(iveiw);
    }

    public void userP(int uid){


        new UserModle().geUser(uid, new UserModle.userListenner() {
            @Override
            public void userFail() {

            }

            @Override
            public void userSuccess(UserBean userBean) {

                if (Iview!=null){

                    Iview.userSuccessData(userBean);
                }

            }
        });

    }
}
