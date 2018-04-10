package wangyuhang.bwie.com.jd_imitate.gwc.model;

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
import wangyuhang.bwie.com.jd_imitate.base.BaseURL;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;

/**
 * Created by dell on 2018/3/30.
 */

public class GwcModel {

    public void gwcM(String uid, final GwcListenner gwcListenner){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("+++++++++++++++",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BaseURL.Login_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String,String> hashMap  = new HashMap<>();
        hashMap.put("source","android");
        hashMap.put("uid",uid);
        Observable<GwcBean> gwc = apiService.gwc(hashMap);

        gwc.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GwcBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("+++++++++++++++++",e.getMessage());
                    }

                    @Override
                    public void onNext(GwcBean gwcBean) {

                        if (gwcListenner!=null){
                            gwcListenner.GwcSuccess(gwcBean);
                        }

                    }
                });


    }


    public interface GwcListenner{

        void GwcCallFail();

        void GwcSuccess(GwcBean gwcBean);


    }

}
