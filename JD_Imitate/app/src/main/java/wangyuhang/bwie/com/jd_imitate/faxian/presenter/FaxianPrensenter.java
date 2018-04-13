package wangyuhang.bwie.com.jd_imitate.faxian.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.faxian.bean.FaxianBean;
import wangyuhang.bwie.com.jd_imitate.faxian.model.FaxianModel;
import wangyuhang.bwie.com.jd_imitate.faxian.view.FaxianView;

import static wangyuhang.bwie.com.jd_imitate.R.mipmap.f;

/**
 * Created by dell on 2018/4/10.
 */

public class FaxianPrensenter extends BasePresenter<FaxianView>{


    public FaxianPrensenter(FaxianView iview) {
        super(iview);
    }

    public void faxianP(){


        new FaxianModel().faxianM(new FaxianModel.OnResponseCallBack() {
            @Override
            public void ResponseSuccess(FaxianBean faxianBean) {
                if (Iview!=null){
                        Iview.SucessCallBack(faxianBean);
                }
            }
        });
    }
}
