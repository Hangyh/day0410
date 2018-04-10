package wangyuhang.bwie.com.jd_imitate.modle;

/**
 * Created by dell on 2018/3/14.
 */

public class FenLeiZiLeiModle {


    public void ziLei(int cid, final OkFeiLeiDataCallBack okFeiLeiDataCallBack){
        String url = "https://www.zhaoapi.cn/product/getProductCatagory"+"?"+"cid="+cid;

        OkUtils.getInstens().doGet(url, new OkUtils.ResponseCallBack() {
            @Override
            public void ResponseFail() {
                if (null!=okFeiLeiDataCallBack){
                    okFeiLeiDataCallBack.DataFail();
                }
            }

            @Override
            public void ResponseSucceess(String s) {
                if (null!=okFeiLeiDataCallBack){
                    okFeiLeiDataCallBack.DataSuccess(s);
                }
            }
        });
    }

    public interface OkFeiLeiDataCallBack{

        void DataFail();

        void DataSuccess(String s);

    }
}
