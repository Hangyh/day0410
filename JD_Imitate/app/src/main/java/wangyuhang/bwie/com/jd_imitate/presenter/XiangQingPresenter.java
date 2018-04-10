package wangyuhang.bwie.com.jd_imitate.presenter;

import wangyuhang.bwie.com.jd_imitate.view.XiangQingView;
import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.modle.XiangQing;

/**
 * Created by dell on 2018/3/14.
 */

public class XiangQingPresenter extends BasePresenter<XiangQingView>{


    public XiangQingPresenter(XiangQingView iview) {
        super(iview);
    }


    public void XiangQ(int pid){

        new XiangQing().XiangQing(pid, new XiangQing.pIdCallListenner() {
            @Override
            public void IdFail() {
                if (null!=Iview){
                    Iview.XiangQingFail();
                }
            }

            @Override
            public void IdSuccess(String s) {
                if (null!=Iview){
                    Iview.XiangQingSuccess(s);
                }
            }
        });

    }
}
