package wangyuhang.bwie.com.jd_imitate.adddizi.presenter;

import wangyuhang.bwie.com.jd_imitate.adddizi.bean.AddBean;
import wangyuhang.bwie.com.jd_imitate.adddizi.model.AddModel;
import wangyuhang.bwie.com.jd_imitate.adddizi.view.AddView;
import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;

/**
 * Created by dell on 2018/4/12.
 */

public class AddPresenter extends BasePresenter<AddView>{

    public AddPresenter(AddView iview) {
        super(iview);
    }

    public void addP(String uid, String addr, String mobile, String kson){


        new AddModel().addM(uid, addr, mobile, kson, new AddModel.addListenner() {
            @Override
            public void addFail() {

            }

            @Override
            public void addSuccess(AddBean addBean) {

                if (Iview!=null){
                    Iview.adddiziSuccess(addBean);
                }

            }
        });

    }

}
