package wangyuhang.bwie.com.jd_imitate.adddizi.api;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.adddizi.bean.AddBean;

/**
 * Created by dell on 2018/4/12.
 */

public interface ApiService {


    @GET("user/addAddr")
    Observable<AddBean> adddizi(@QueryMap HashMap<String,String> map);
    //?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson

}
