package wangyuhang.bwie.com.jd_imitate.presenter;


import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.modle.ShowModle;
import wangyuhang.bwie.com.jd_imitate.view.ShowView;

/**
 * Created by dell on 2018/3/12.
 */

public class ShowPresenter extends BasePresenter<ShowView> {
    public ShowPresenter(ShowView iview) {
        super(iview);
    }

    public void showpresentre(String url){

        new ShowModle().show(url, new ShowModle.OkDataCallBack() {
            @Override
            public void DataFail() {
                if (null!=Iview){
                    Iview.Fail();
                }
            }

            @Override
            public void DataSuccess(String s) {
                if (null!=Iview){
                    Iview.Success(s);
                }
            }
        });

    }

    public void showGrid(String url){

        new ShowModle().show(url, new ShowModle.OkDataCallBack() {
            @Override
            public void DataFail() {

            }

            @Override
            public void DataSuccess(String s) {

                if (null!=Iview){
                    Iview.GridSuccess(s);
                }

            }
        });

    }
}
