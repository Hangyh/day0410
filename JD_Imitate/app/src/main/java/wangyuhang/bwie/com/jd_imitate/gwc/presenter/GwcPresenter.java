package wangyuhang.bwie.com.jd_imitate.gwc.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;
import wangyuhang.bwie.com.jd_imitate.gwc.model.GwcModel;
import wangyuhang.bwie.com.jd_imitate.gwc.view.GwcView;


/**
 * Created by dell on 2018/3/30.
 */

public class GwcPresenter extends BasePresenter<GwcView>{

    public GwcPresenter(GwcView iview) {
        super(iview);
    }

    public void GwcP(String uid){

        new GwcModel().gwcM(uid, new GwcModel.GwcListenner() {
            @Override
            public void GwcCallFail() {

            }

            @Override
            public void GwcSuccess(GwcBean gwcBean) {

                if (Iview!=null){
                    Iview.GwcSuccess(gwcBean);
                }

            }
        });

    }
}
