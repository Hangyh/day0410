package wangyuhang.bwie.com.jd_imitate.gwc.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;

/**
 * Created by dell on 2018/3/30.
 */

public interface GwcView extends BaseView{

    void GwcFail();

    void GwcSuccess(GwcBean gwcBean);
}
