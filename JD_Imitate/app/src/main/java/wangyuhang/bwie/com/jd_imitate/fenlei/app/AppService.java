package wangyuhang.bwie.com.jd_imitate.fenlei.app;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.ZiFenLeiInfo;

/**
 * Created by lenovo on 2018/3/21.
 */

public interface AppService {
@GET("getProducts")
Observable<ZiFenLeiInfo> getZifenlei(@Query("pscid") String pscid);
}
