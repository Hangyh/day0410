package wangyuhang.bwie.com.jd_imitate.gouwuche.view;

import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.MessageBean;

/**
 * Created by dell on 2018/4/9.
 */

public interface Iview {

    void onSuccess(Object o);
    void onFailed(Exception e);
    void delSuccess(MessageBean listMessageBean);
}