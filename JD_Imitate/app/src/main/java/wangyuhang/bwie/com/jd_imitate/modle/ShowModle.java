package wangyuhang.bwie.com.jd_imitate.modle;

/**
 * Created by dell on 2018/3/12.
 */

public class ShowModle {


    public void show(String url, final OkDataCallBack okDataCallBack){

        OkUtils.getInstens().doGet(url, new OkUtils.ResponseCallBack() {
            @Override
            public void ResponseFail() {
                if (null!=okDataCallBack){
                    okDataCallBack.DataFail();
                }
            }

            @Override
            public void ResponseSucceess(String s) {
                if (null!=okDataCallBack){
                    okDataCallBack.DataSuccess(s);
                }
            }
        });

    }




    public interface OkDataCallBack{

        void DataFail();

        void DataSuccess(String s);

    }
}
