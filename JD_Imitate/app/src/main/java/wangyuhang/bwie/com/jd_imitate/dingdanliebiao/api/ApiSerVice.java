package wangyuhang.bwie.com.jd_imitate.dingdanliebiao.api;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;

/**
 * Created by dell on 2018/4/13.
 */

public interface ApiSerVice {

    @GET("product/getOrders")
    Observable<DingDanBean> showdingdan(@QueryMap HashMap<String,String> map);
}
