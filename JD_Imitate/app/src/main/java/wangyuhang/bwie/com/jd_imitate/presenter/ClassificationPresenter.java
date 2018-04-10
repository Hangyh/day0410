package wangyuhang.bwie.com.jd_imitate.presenter;

import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;
import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.modle.ShowModle;
import wangyuhang.bwie.com.jd_imitate.view.ClassificationView;

/**
 * Created by dell on 2018/3/14.
 */

public class ClassificationPresenter extends BasePresenter<ClassificationView> {


    public ClassificationPresenter(ClassificationView iview) {
        super(iview);
    }

    public void ficationView(String url){

        new ShowModle().show(url, new ShowModle.OkDataCallBack() {
            @Override
            public void DataFail() {

            }

            @Override
            public void DataSuccess(String s) {
                if (null!=Iview){
                    Iview.ClassificationViewSuccess(s);
                }
            }
        });
    }
}
