package wangyuhang.bwie.com.jd_imitate.fenlei.presenter;

import android.util.Log;

import wangyuhang.bwie.com.jd_imitate.fenlei.bean.ZiFenLeiInfo;
import wangyuhang.bwie.com.jd_imitate.fenlei.model.IModel;
import wangyuhang.bwie.com.jd_imitate.fenlei.view.IView;


/**
 * Created by lenovo on 2018/3/21.
 */

public class IPresenter {

    IModel iModel;
    IView iView;

    public IPresenter(IView iView) {
        this.iView = iView;
        iModel=new IModel();
    }

    public void getIPresenter(String i){
        iModel.getIModel(i);
        iModel.setOnListenerModel(new IModel.setNewModel() {
            @Override
            public void getSuccess(ZiFenLeiInfo ziFenLeiInfo) {
                iView.OnSuccess(ziFenLeiInfo);
                Log.d("AAAgetIPresenter",ziFenLeiInfo.getMsg());
            }
        });
    }
}
