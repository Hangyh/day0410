package wangyuhang.bwie.com.jd_imitate.dizi.api;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;

/**
 * Created by dell on 2018/4/12.
 */

public interface ApiSerVice {


    @GET("user/getAddrs")
    Observable<DiziBean> getdizi(@QueryMap HashMap<String,String> map);

}
