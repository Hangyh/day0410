package wangyuhang.bwie.com.jd_imitate.modle;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import wangyuhang.bwie.com.jd_imitate.bean.LoginBean;
import wangyuhang.bwie.com.jd_imitate.bean.RegisBean;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;

/**
 * Created by dell on 2018/3/16.
 */

public interface ApiSerVice {
//    user/login


    @GET("/user/login")
    Observable<LoginBean> getLogin(@QueryMap Map<String,String> map);
    @GET("user/reg")
    Observable<RegisBean> getRegis(@QueryMap Map<String,String> map2);
    @GET("user/getUserInfo")
    Observable<UserBean> getUser(@QueryMap HashMap<String, String> hashMap);
    //          product/searchProducts?keywords=笔记本&page=1
    @GET("product/searchProducts")
    Observable<ShowBean> getShow(@QueryMap HashMap<String,String> map3);

    @GET("user/updateNickName")
    Observable<XiuGaiBean> updateName(@QueryMap HashMap<String,String> map4);

}
