package wangyuhang.bwie.com.jd_imitate.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wangyuhang.bwie.com.jd_imitate.interceptor.LoggingInterceptor;
import wangyuhang.bwie.com.jd_imitate.modle.ApiSerVice;


/**
 * Created by dell on 2018/3/27.
 */


public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private ApiSerVice apiService;
    private OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
    private RetrofitUtils(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://120.27.23.105/")
                .build();
        apiService = retrofit.create(ApiSerVice.class);
    }
    public static RetrofitUtils getInstance(){
        if (null==instance){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    public ApiSerVice getApiService(){
        return apiService;
    }
}