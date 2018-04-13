package wangyuhang.bwie.com.jd_imitate.faxian.api;

import retrofit2.http.GET;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.faxian.bean.FaxianBean;

/**
 * Created by dell on 2018/4/10.
 */

public interface ApiService {
///http://v.juhe.cn/toutiao/index?type=top&key=205706b7d07479f45345e17e7052aecd
    @GET("index?type=top&key=205706b7d07479f45345e17e7052aecd")
    Observable<FaxianBean> getshowFaxian();

}
