package wangyuhang.bwie.com.jd_imitate.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;


/**
 * Created by dell on 2018/3/17.
 */

public interface UserView extends BaseView {

    void userFailData();
    void userSuccessData(UserBean userBean);


}
