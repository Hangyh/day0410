package wangyuhang.bwie.com.jd_imitate.dingdanliebiao.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.model.DingDanLieBiaoModel;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.view.DingDanLieBiaoView;

/**
 * Created by dell on 2018/4/13.
 */

public class DingDanLieBiaoPresenter extends BasePresenter<DingDanLieBiaoView> {

    public DingDanLieBiaoPresenter(DingDanLieBiaoView iview) {
        super(iview);
    }

    public void dingdanP(String uid){
        new DingDanLieBiaoModel().dingdanM(uid, new DingDanLieBiaoModel.dingdanListenner() {
            @Override
            public void dingdanFail() {

            }

            @Override
            public void dingdanSuccess(DingDanBean dingDanBean) {

                if (Iview!=null){
                    Iview.dingdanliebiaoSuccess(dingDanBean);
                }

            }
        });
    }

}
