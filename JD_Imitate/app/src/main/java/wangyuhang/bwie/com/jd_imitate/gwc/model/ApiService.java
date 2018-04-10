package wangyuhang.bwie.com.jd_imitate.gwc.model;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;

/**
 * Created by dell on 2018/3/30.
 */

public interface ApiService {

    @GET("product/getCarts")
    Observable<GwcBean> gwc(@QueryMap HashMap<String,String> map);
}
