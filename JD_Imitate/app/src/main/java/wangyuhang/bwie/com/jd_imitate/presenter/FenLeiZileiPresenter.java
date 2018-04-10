package wangyuhang.bwie.com.jd_imitate.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.modle.FenLeiZiLeiModle;
import wangyuhang.bwie.com.jd_imitate.view.FenLeiZilei;

/**
 * Created by dell on 2018/3/14.
 */

public class FenLeiZileiPresenter extends BasePresenter<FenLeiZilei>{

    public FenLeiZileiPresenter(FenLeiZilei iview) {
        super(iview);
    }

    public void zilei(int cid){

        new FenLeiZiLeiModle().ziLei(cid, new FenLeiZiLeiModle.OkFeiLeiDataCallBack() {
            @Override
            public void DataFail() {
                if (Iview!=null){
                    Iview.FenLeiZileiFail();
                }
            }

            @Override
            public void DataSuccess(String s) {
                if (Iview!=null){
                    Iview.FenLeiZileiSuccess(s);
                }
            }
        });

    }
}
