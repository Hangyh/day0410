package wangyuhang.bwie.com.jd_imitate.dizi.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;
import wangyuhang.bwie.com.jd_imitate.dizi.model.ZhanshiDiziModel;
import wangyuhang.bwie.com.jd_imitate.dizi.view.ZhanshiDiziView;

/**
 * Created by dell on 2018/4/12.
 */

public class ZhanshiDiziPresenter extends BasePresenter<ZhanshiDiziView>{

    public ZhanshiDiziPresenter(ZhanshiDiziView iview) {
        super(iview);
    }

    public void zhanshiP(String uid){

        new ZhanshiDiziModel().diziM(uid, new ZhanshiDiziModel.diziListenner() {
            @Override
            public void diziFail() {

            }

            @Override
            public void diziSuccess(DiziBean diziBean) {
                if (Iview!=null){

                    Iview.zhanshiSuccess(diziBean);
                }
            }
        });

    }
}
