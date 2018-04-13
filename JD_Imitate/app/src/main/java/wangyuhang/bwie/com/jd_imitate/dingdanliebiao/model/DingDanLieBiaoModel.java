package wangyuhang.bwie.com.jd_imitate.dingdanliebiao.model;

import android.util.Log;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.api.ApiSerVice;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;


/**
 * Created by dell on 2018/4/13.
 */

public class DingDanLieBiaoModel {

    public void dingdanM(String uid, final dingdanListenner listenner){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("message",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        ApiSerVice apiSerVice = retrofit.create(ApiSerVice.class);

        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("token","android");
        hashmap.put("uid",uid);
        Observable<DingDanBean> showdingdan = apiSerVice.showdingdan(hashmap);

        showdingdan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DingDanBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DingDanBean dingDanBean) {

                        if (listenner!=null){
                            listenner.dingdanSuccess(dingDanBean);
                        }

                    }
                });
    }

    public interface dingdanListenner{

        void dingdanFail();

        void dingdanSuccess(DingDanBean dingDanBean);

    }
}
