package wangyuhang.bwie.com.jd_imitate.modle;

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
import wangyuhang.bwie.com.jd_imitate.bean.LoginBean;
import wangyuhang.bwie.com.jd_imitate.bean.RegisBean;

/**
 * Created by dell on 2018/3/17.
 */

public class LoginRegisModle {

    public void Registor(String mobile, String password, final ResoponsCallBack resoponsCallBack){


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BaseURL.Login_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiSerVice apiSerVice = retrofit.create(ApiSerVice.class);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("mobile",mobile);
        hashMap.put("password",password);
        Observable<RegisBean> regis = apiSerVice.getRegis(hashMap);

        regis.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RegisBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegisBean regisBean) {

                        if (resoponsCallBack!=null){
                            resoponsCallBack.ResgitSuccess(regisBean);
                        }

                    }
                });
    }

    public void loginModle(String mobile, String password, final LoginCallBack loginCallBack){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BaseURL.Login_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiSerVice apiSerVice = retrofit.create(ApiSerVice.class);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("mobile",mobile);
        hashMap.put("password",password);
        Observable<LoginBean> login = apiSerVice.getLogin(hashMap);

        login.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {

                        if (loginCallBack!=null){
                            loginCallBack.LoginSucess(loginBean);
                        }

                    }
                });


    }


    public interface ResoponsCallBack{


        void ResgitFail();
        void ResgitSuccess(RegisBean regisBean);
    }

    public interface LoginCallBack{
        void LoginFai();
        void LoginSucess(LoginBean loginBean);
    }

}
