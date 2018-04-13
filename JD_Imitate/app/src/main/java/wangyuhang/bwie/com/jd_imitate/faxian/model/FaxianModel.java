package wangyuhang.bwie.com.jd_imitate.faxian.model;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wangyuhang.bwie.com.jd_imitate.faxian.api.ApiService;
import wangyuhang.bwie.com.jd_imitate.faxian.bean.FaxianBean;

/**
 * Created by dell on 2018/4/10.
 */

public class FaxianModel {

    public void faxianM(final OnResponseCallBack responseCallBack){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.i("message",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://v.juhe.cn/toutiao/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
//http://v.juhe.cn/toutiao/index?type=top&key=205706b7d07479f45345e17e7052aecd
        Observable<FaxianBean> faxianBeanObservable = apiService.getshowFaxian();

         faxianBeanObservable.observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.io())
                 .subscribe(new Subscriber<FaxianBean>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(FaxianBean faxianBean) {

                         if (responseCallBack!=null){
                             responseCallBack.ResponseSuccess(faxianBean);
                         }

                     }
                 });
    }

    public interface OnResponseCallBack{

        void ResponseSuccess(FaxianBean faxianBean);

    }
}
