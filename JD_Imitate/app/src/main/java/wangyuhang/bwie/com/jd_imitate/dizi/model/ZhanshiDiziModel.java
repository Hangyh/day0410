package wangyuhang.bwie.com.jd_imitate.dizi.model;

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
import wangyuhang.bwie.com.jd_imitate.dizi.api.ApiSerVice;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;

/**
 * Created by dell on 2018/4/12.
 */

public class ZhanshiDiziModel {

    public void diziM(String uid, final diziListenner listenner){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("dizi",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiSerVice apiSerVice = retrofit.create(ApiSerVice.class);

        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("token","android");
        hashmap.put("uid",uid);
        Observable<DiziBean> getdizi = apiSerVice.getdizi(hashmap);

        getdizi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DiziBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DiziBean diziBean) {

                        if (listenner!=null){
                            listenner.diziSuccess(diziBean);
                        }

                    }
                });

    }
    public interface diziListenner{

        void diziFail();

        void diziSuccess(DiziBean diziBean);

    }
}
