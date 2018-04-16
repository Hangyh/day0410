package wangyuhang.bwie.com.jd_imitate.adddizi.model;

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
import wangyuhang.bwie.com.jd_imitate.adddizi.api.ApiService;
import wangyuhang.bwie.com.jd_imitate.adddizi.bean.AddBean;

/**
 * Created by dell on 2018/4/12.
 */

public class AddModel {

    public void addM(String uid, String addr, String mobile, String kson, final addListenner listenner){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.i("add",message);
            }
        });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        HashMap<String, String> hashmap = new HashMap<>();
        //uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
        hashmap.put("source","android");
        hashmap.put("uid",uid);
        hashmap.put("addr",addr);
        hashmap.put("mobile",mobile);
        hashmap.put("name",kson);
        Observable<AddBean> adddizi = apiService.adddizi(hashmap);

        adddizi.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AddBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("-------------",e.getMessage());

                    }

                    @Override
                    public void onNext(AddBean addBean) {

                        if (listenner!=null){
                            listenner.addSuccess(addBean);
                        }

                    }
                });
    }

    public interface addListenner{

        void addFail();

        void addSuccess(AddBean addBean);
    }
}
