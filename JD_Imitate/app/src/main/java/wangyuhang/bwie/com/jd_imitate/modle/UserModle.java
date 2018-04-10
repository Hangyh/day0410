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
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;


/**
 * Created by dell on 2018/3/17.
 */

public class UserModle {


    public void geUser(int uid, final userListenner userListenner){

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
        hashMap.put("token","android");
        hashMap.put("uid",uid+"");
        final Observable<UserBean> user = apiSerVice.getUser(hashMap);

        user.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {

                        if (userListenner!=null){
                            userListenner.userSuccess(userBean);
                        }

                    }
                });
    }

    public interface userListenner{
        void userFail();
        void userSuccess(UserBean userBean);

    }

}
