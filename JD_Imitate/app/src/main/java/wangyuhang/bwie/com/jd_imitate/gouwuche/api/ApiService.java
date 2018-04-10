package wangyuhang.bwie.com.jd_imitate.gouwuche.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.DatasBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.MessageBean;


/**
 * Created by dell on 2018/4/9.
 */

public interface ApiService {

    @GET("product/getCarts")
    Flowable<MessageBean<List<DatasBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);
}