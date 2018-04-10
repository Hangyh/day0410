package wangyuhang.bwie.com.jd_imitate.modle;

/**
 * Created by dell on 2018/3/14.
 */

public class XiangQing {

    public void XiangQing(int pid, final pIdCallListenner pIdCallListenner){

        String url = "https://www.zhaoapi.cn/product/getProductDetail"+"?+source=android&"+"psid="+pid;

        OkUtils.getInstens().doGet(url, new OkUtils.ResponseCallBack() {
            @Override
            public void ResponseFail() {

                if (pIdCallListenner!=null){
                    pIdCallListenner.IdFail();
                }
            }

            @Override
            public void ResponseSucceess(String s) {
                if (pIdCallListenner!=null){
                    pIdCallListenner.IdSuccess(s);
                }
            }
        });
    }

    public interface pIdCallListenner{

        void IdFail();

        void IdSuccess(String s);

    }
}
