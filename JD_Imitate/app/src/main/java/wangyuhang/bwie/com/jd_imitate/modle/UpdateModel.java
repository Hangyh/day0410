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
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;

import static wangyuhang.bwie.com.jd_imitate.R.id.i;

/**
 * Created by dell on 2018/3/28.
 */

public class UpdateModel {


    public void updateM(String uid, String nickName,String token, final updateListenner updateListenner){



        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("qqq",message);
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
        hashMap.put("token",token);
        hashMap.put("uid",uid);
        hashMap.put("nickname",nickName);
        Observable<XiuGaiBean> xiuGaiBeanObservable = apiSerVice.updateName(hashMap);

        xiuGaiBeanObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<XiuGaiBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("TAG",e.getMessage());

                    }

                    @Override
                    public void onNext(XiuGaiBean xiuGaiBean) {

                        if (updateListenner!=null){
                            updateListenner.updateSuccess(xiuGaiBean);
                        }

                    }
                });

    }

    public interface updateListenner{

        void updateFail();

        void updateSuccess(XiuGaiBean xiuGaiBean);

    }
}
