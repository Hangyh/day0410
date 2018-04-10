package wangyuhang.bwie.com.jd_imitate.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;

/**
 * Created by dell on 2018/3/28.
 */

public interface UpdateView extends BaseView{

    void xiugaiFail();

    void xiugaiSuccess(XiuGaiBean xiuGaiBean);
}
