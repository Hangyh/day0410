package wangyuhang.bwie.com.jd_imitate.base;

import android.content.Context;

import wangyuhang.bwie.com.jd_imitate.MyApp;

/**
 * Created by dell on 2018/3/14.
 */

public class BasePresenter<V extends BaseView> {

    protected V Iview;

    public BasePresenter(V iview) {
        Iview = iview;
    }
    public void onDestroy(){

        Iview=null;
    }

    public Context getContet(){

        if (null!=Iview&&null!=Iview.context()){
            return Iview.context();
        }
        return MyApp.getMyApp();
    }
}
