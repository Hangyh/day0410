package wangyuhang.bwie.com.jd_imitate.dizi.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;

/**
 * Created by dell on 2018/4/12.
 */

public interface ZhanshiDiziView extends BaseView{


    void zhanshiFail();

    void zhanshiSuccess(DiziBean diziBean);
}
