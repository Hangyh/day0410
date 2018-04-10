package wangyuhang.bwie.com.jd_imitate.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;
import wangyuhang.bwie.com.jd_imitate.modle.UpdateModel;
import wangyuhang.bwie.com.jd_imitate.view.UpdateView;

/**
 * Created by dell on 2018/3/28.
 */

public class UpdatePresenter extends BasePresenter<UpdateView>{
    public UpdatePresenter(UpdateView iview) {
        super(iview);
    }

    public void updateP(String uid,String nickName,String token){

        new UpdateModel().updateM(uid, nickName,token, new UpdateModel.updateListenner() {
            @Override
            public void updateFail() {

            }

            @Override
            public void updateSuccess(XiuGaiBean xiuGaiBean) {

                if (Iview!=null){
                    Iview.xiugaiSuccess(xiuGaiBean);
                }

            }
        });

    }
}
